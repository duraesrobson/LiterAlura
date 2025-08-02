package br.com.alura.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.literalura.literalura.dto.DadosAutor;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class    Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoNascimento;
    private Integer anoFalecimento;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalecimento = dadosAutor.anoFalecimento();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getAnoNascimento() { return anoNascimento; }
    public void setAnoNascimento(Integer anoNascimento) { this.anoNascimento = anoNascimento; }

    public Integer getAnoFalecimento() { return anoFalecimento; }
    public void setAnoFalecimento(Integer anoFalecimento) { this.anoFalecimento = anoFalecimento; }

    public List<Livro> getLivros() { return livros; }
    public void setLivros(List<Livro> livros) { this.livros = livros; }

    @Override
    public String toString() {
        StringBuilder livrosStr = new StringBuilder("Livros: ");
        for (int i = 0; i < livros.size(); i++) {
            livrosStr.append(livros.get(i).getTitulo());
            if (i < livros.size() - 1) {
                livrosStr.append(", ");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n********** Autor **********");
        sb.append("\nNome: ").append(nome);
        sb.append("\n").append(livrosStr.toString());
        sb.append("\nAno de Nascimento: ").append(anoNascimento);
        sb.append("\nAno de Falecimento: ").append(anoFalecimento);
        sb.append("\n***************************");
        return sb.toString();

    }
}
