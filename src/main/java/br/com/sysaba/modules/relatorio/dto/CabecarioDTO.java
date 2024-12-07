package br.com.sysaba.modules.relatorio.dto;

public class CabecarioDTO {
    private String descricao;

    public CabecarioDTO() {}

    public CabecarioDTO(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
