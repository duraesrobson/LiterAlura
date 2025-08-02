package br.com.alura.literalura.literalura.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.literalura.literalura.dto.DadosAutor;
import br.com.alura.literalura.literalura.dto.DadosLivro;
import br.com.alura.literalura.literalura.dto.RespostaApi;
import br.com.alura.literalura.literalura.model.Autor;
import br.com.alura.literalura.literalura.model.Idioma;
import br.com.alura.literalura.literalura.model.Livro;
import br.com.alura.literalura.literalura.repository.AutorRepository;
import br.com.alura.literalura.literalura.repository.LivroRepository;
import br.com.alura.literalura.literalura.util.ConsultaAPI;
import br.com.alura.literalura.literalura.util.ConversorDeJson;
import jakarta.transaction.Transactional;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsultaAPI consultaAPI = new ConsultaAPI();
    private ConversorDeJson conversor = new ConversorDeJson();
    private String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repositorioLivro;
    private AutorRepository repositorioAutor;

    public Principal(LivroRepository repositorioLivro, AutorRepository repositorioAutor){
        this.repositorioLivro = repositorioLivro;
        this.repositorioAutor = repositorioAutor;
    }

    public void exibeMenu(){
        var menu = ("""

                ************* LiterAlura *************

                1 - Buscar livro pelo título 
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma

                0 - Sair
                """);

        var escolha = -1;
        while (escolha != 0) {
            System.out.println(menu);
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    buscarLivro();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }

    
    private RespostaApi getDadosLivro() {
        System.out.print("Digite o nome do livro para buscar: ");
        var nomeLivro = scanner.nextLine();
        var json = consultaAPI.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        
        RespostaApi resposta = conversor.converterJsonParaObjeto(json, RespostaApi.class);
        return resposta;
    }

    private void buscarLivro() {
    RespostaApi dadosBusca = getDadosLivro();

    if (dadosBusca != null && !dadosBusca.results().isEmpty()) {
        for (DadosLivro dadoLivro : dadosBusca.results()) {
            Livro livro = new Livro(dadoLivro);

            System.out.println(livro);

            Optional<Livro> livroExistente = repositorioLivro.findByTitulo(livro.getTitulo());

            if (livroExistente.isPresent()) {
                System.out.println("\nO livro " + livro.getTitulo() + " já está registrado!\n");
                continue;
            }

            List<Autor> autores = new ArrayList<>();

            for (DadosAutor dadosAutor : dadoLivro.autores()) {
                Autor autor = new Autor(dadosAutor);

                Optional<Autor> autorExistente = repositorioAutor.findByNome(autor.getNome());

                if (autorExistente.isPresent()) {
                    autores.add(autorExistente.get());
                } else {
                    Autor novoAutor = repositorioAutor.save(autor);
                    autores.add(novoAutor);
                }
            }

            livro.setAutores(autores);
            repositorioLivro.save(livro);

            System.out.println("********** Livro Cadastrado **********");
            System.out.printf("Título: %s%nAutores: %s%nIdiomas: %s%nDownloads: %d%n",
                    livro.getTitulo(),
                    autores.stream().map(Autor::getNome).collect(Collectors.joining(", ")),
                    livro.getIdioma(),
                    livro.getDownloads() != null ? livro.getDownloads() : 0);
            System.out.println("**************************************");
        }
    }
}

    
    @Transactional
    private void listarLivrosRegistrados() {
        System.out.println("\nLivros buscados:");
        List<Livro> livros = new ArrayList<>();
        livros = repositorioLivro.findAll();
        livros.stream()
            .sorted(Comparator.comparing(Livro::getTitulo))
            .forEach(System.out::println);
    }

    @Transactional
    private void listarAutoresRegistrados() {
        System.out.println("\nAutores buscados:");  
        List<Autor> autores = new ArrayList<>();
        autores = repositorioLivro.listarAutoresBuscados();
        autores.stream()
                .forEach(System.out::println);
        }

    private void listarAutoresVivos() {
        System.out.println("\nQual o ano que deseja buscar?");
        var ano = scanner.nextInt();

        List<Autor> autores = repositorioAutor.findAutoresVivosEmAno(ano);

        for(Autor autor : autores){
            
            // List<Livro> livros = autor.getLivros();
            // String nomesLivros = livros.stream()
            //                     .map(Livro::getTitulo)
            //                     .collect(Collectors.joining(", "));
                                
            System.out.println(autor.toString());
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("\nQual o idioma? (Ex: pt, en, fr, etc)");
        var idioma = scanner.nextLine();
        Idioma idiomaPesquisado;

        idiomaPesquisado = Idioma.fromString("["+idioma+"]");
        List<Livro> livros = repositorioLivro.findByIdioma(idiomaPesquisado);

        livros.stream()
            .sorted(Comparator.comparing(Livro::getTitulo))
            .forEach(System.out::println);

    }
    
}
