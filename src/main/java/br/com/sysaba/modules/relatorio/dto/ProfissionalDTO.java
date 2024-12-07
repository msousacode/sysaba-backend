package br.com.sysaba.modules.relatorio.dto;

public class ProfissionalDTO {
    private String nome;
    private String registro;

    public ProfissionalDTO() {}

    public ProfissionalDTO(String nome, String registro) {
        this.nome = nome;
        this.registro = registro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
}
