package br.com.alura.literalura.literalura.principal;

import java.util.List;
import java.util.Scanner;

import br.com.alura.literalura.literalura.model.DadosLivro;
import br.com.alura.literalura.literalura.model.DadosResposta;
import br.com.alura.literalura.literalura.model.Livro;
import br.com.alura.literalura.literalura.service.ConsultaAPI;
import br.com.alura.literalura.literalura.service.ConverteDados;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private ConsultaAPI consultaAPI = new ConsultaAPI();
    private ConverteDados conversor = new ConverteDados();
    private String ENDERECO = "https://gutendex.com/books/?search=";

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
            // repositorio.save(livro);
            System.out.println("\n" + livro);
        }
    }

    private List<DadosLivro> getDadosLivro() {
        System.out.print("Digite o nome do livro para buscar: ");
        var nomeLivro = scanner.nextLine();
        var json = consultaAPI.obterDados(ENDERECO + nomeLivro.replace(" ", "%20"));

        DadosResposta resposta = conversor.obterDados(json, DadosResposta.class);
        return resposta.results();
    }
    
}
