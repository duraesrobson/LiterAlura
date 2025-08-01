package br.com.alura.literalura.literalura.model;

import java.util.List;

import br.com.alura.literalura.literalura.dto.DadosAutor;
import br.com.alura.literalura.literalura.dto.DadosLivro;

public class Livro {
    private String titulo;
    private List<DadosAutor> autores;
    private List<String> idioma;
    private Integer downloads;

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.autores = dadosLivro.autores();
        this.idioma = dadosLivro.idiomas();
        this.downloads = dadosLivro.downloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<DadosAutor> getAutores() {
        return autores;
    }

    public void setAutores(List<DadosAutor> autores) {
        this.autores = autores;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(titulo);
        sb.append(",\nAutores: ").append(autores);
        sb.append(",\nIdiomas: ").append(idioma);
        sb.append(",\nDownloads: ").append(downloads).append("\n");
        return sb.toString();
    }
    
}
