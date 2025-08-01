package br.com.alura.literalura.literalura.principal;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.literalura.literalura.dto.DadosLivro;
import br.com.alura.literalura.literalura.dto.RespostaApi;
import br.com.alura.literalura.literalura.model.Autor;
import br.com.alura.literalura.literalura.model.Livro;
import br.com.alura.literalura.literalura.repository.LivroRepository;
import br.com.alura.literalura.literalura.util.ConsultaAPI;
import br.com.alura.literalura.literalura.util.ConversorDeJson;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsultaAPI consultaAPI = new ConsultaAPI();
    private ConversorDeJson conversor = new ConversorDeJson();
    private String ENDERECO = "https://gutendex.com/books/?search=";

    private LivroRepository repositorioLivro;

    public Principal(LivroRepository repositorioLivro){
        this.repositorioLivro = repositorioLivro;
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

    
    private void buscarLivro() {
        List<DadosLivro> livros = getDadosLivro();

        for(DadosLivro dados: livros){
            Livro livro = new Livro(dados);
            // dadosSeries.add(dados);
            repositorioLivro.save(livro);
            System.out.println("\n" + livro);
        }
    }
    
    private List<DadosLivro> getDadosLivro() {
        System.out.print("Digite o nome do livro para buscar: ");
        var nomeLivro = scanner.nextLine();
        var json = consultaAPI.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));
        
        RespostaApi resposta = conversor.converterJsonParaObjeto(json, RespostaApi.class);
        return resposta.results();
    }

    private void listarLivrosRegistrados() {
        System.out.println("\nLivros buscados:");
        var livros = repositorioLivro.findAll();

        livros.stream()
            .sorted(Comparator.comparing(Livro::getTitulo))
            .forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {
        System.out.println("\nAutores buscados:");  
        List<Autor> autores = repositorioLivro.listarAutoresBuscados();

        for(Autor autor : autores){
            
            List<Livro> livros = autor.getLivros();
            String nomesLivros = livros.stream()
                                .map(Livro::getTitulo)
                                .collect(Collectors.joining(", "));
                                
            System.out.println(autor.getNome() + " - Livros: " + nomesLivros);
        }
    }

    private void listarAutoresVivos() {
        System.out.println("\nQual o ano que deseja buscar?");
        var ano = scanner.nextInt();

        List<Autor> autores = repositorioLivro.findAutoresVivosEmAno(ano);

        for(Autor autor : autores){
            
            List<Livro> livros = autor.getLivros();
            String nomesLivros = livros.stream()
                                .map(Livro::getTitulo)
                                .collect(Collectors.joining(", "));
                                
            System.out.println(autor.getNome() + " - Ano de falecimento: " + autor.getAnoFalecimento() + " - Livros: " + nomesLivros);
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("\nQual o idioma? (Ex: pt, en, fr, etc)");
        String idioma = scanner.nextLine();

        List<Livro> livros = repositorioLivro.findByIdiomas(idioma);

        livros.stream()
            .sorted(Comparator.comparing(Livro::getTitulo))
            .forEach(System.out::println);

    }
    
}
