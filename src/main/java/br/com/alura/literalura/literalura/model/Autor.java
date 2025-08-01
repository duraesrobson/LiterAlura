package br.com.alura.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.literalura.literalura.dto.DadosAutor;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anoNascimento;
    private Integer anoFalescimento;
    
    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
        return livros;
    }

    public Autor(){}

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.anoNascimento = dadosAutor.anoNascimento();
        this.anoFalescimento = dadosAutor.anoFalescimento();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getAnoNascimento() {
        return anoNascimento;
    }
    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    public Integer getAnoFalescimento() {
        return anoFalescimento;
    }
    public void setAnoFalescimento(Integer anoFalescimento) {
        this.anoFalescimento = anoFalescimento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome);
        sb.append(",\nAno de nascimento: ").append(anoNascimento);
        sb.append(",\nAno de falescimento: ").append(anoFalescimento).append("\n");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

}
