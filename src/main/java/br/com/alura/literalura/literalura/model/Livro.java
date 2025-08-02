package br.com.alura.literalura.literalura.model;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.literalura.literalura.dto.DadosLivro;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String titulo;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
        name = "livros_autores",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Integer downloads;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        
        if (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()) {
            this.idioma = Idioma.fromString(dadosLivro.idiomas().toString().split(",")[0].trim());
        } else {
            this.idioma = Idioma.DESCONHECIDO;
        }

        this.downloads = dadosLivro.downloads();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Idioma getIdioma() { return idioma; }
    public void setIdioma(Idioma idioma) { this.idioma = idioma; }

    public Integer getDownloads() { return downloads; }
    public void setDownloads(Integer downloads) { this.downloads = downloads; }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }

    @Override
    public String toString() {
        StringBuilder autoresStr = new StringBuilder();
        for (int i = 0; i < autores.size(); i++) {
            autoresStr.append(autores.get(i).getNome());
            if (i < autores.size() - 1) {
                autoresStr.append(", ");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("\n********** LIVRO **********\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autores: ").append(autoresStr.toString()).append("\n");
        sb.append("Idioma: ").append(idioma).append("\n");
        sb.append("Downloads: ").append(downloads).append("\n");
        sb.append("***************************");

        return sb.toString();

    }
}
