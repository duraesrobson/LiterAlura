package br.com.alura.literalura.literalura.principal;

import java.util.Scanner;

import br.com.alura.literalura.literalura.service.ConsultaAPI;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    ConsultaAPI consultaAPI = new ConsultaAPI();
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
        System.out.println("\nDigite o livro que deseja informações: ");
        var busca = scanner.nextLine();

        var resultado = consultaAPI.obterDados(ENDERECO + busca);
        System.out.println(resultado);
    }

    
}
