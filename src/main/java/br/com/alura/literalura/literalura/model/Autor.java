package br.com.alura.literalura.literalura.model;

public class Autor {
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalescimento;
    
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

}
