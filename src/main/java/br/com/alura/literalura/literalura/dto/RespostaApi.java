package br.com.alura.literalura.literalura.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaApi(List<DadosLivro> results) {
    
}
