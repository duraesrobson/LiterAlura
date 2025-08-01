package br.com.alura.literalura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
    @JsonAlias("title") String titulo,
    @JsonAlias("authors") List<DadosAutor> autores,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Integer downloads
) {
@Override
public final String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("\nTítulo: ").append(titulo);
    sb.append("\nAutores: ").append(autores);
    sb.append("\nIdiomas: ").append(idiomas);
    sb.append("\nDownloads: ").append(downloads);
    return sb.toString();
}
}
