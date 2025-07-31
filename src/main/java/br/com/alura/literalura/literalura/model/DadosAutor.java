package br.com.alura.literalura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAutor(
    @JsonAlias("name") String nome,
    @JsonAlias("birth_year") Integer anoNascimento,
    @JsonAlias("death_year") Integer anoFalescimento
) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome);
        sb.append(",\nAno de nascimento: ").append(anoNascimento);
        sb.append(",\nAno de falescimento: ").append(anoFalescimento).append("\n");
        return sb.toString();
    }    
}
