package br.com.sysaba.modules.relatorio.dto;

public class AprendizDTO {
    private String nome;
    private String nascimento;

    public AprendizDTO() {}

    public AprendizDTO(String nome, String nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
