package br.com.alura.literalura.literalura.principal;

public class Principal {

    public void exibeMenu(){
        System.out.println("""

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
            switch (escolha) {
                case 1:
                    buscarLivro();
                    break;
                default:
                    System.out.println("Digite uma opção válida");
            }
        }
    }
}
