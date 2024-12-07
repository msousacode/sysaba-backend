package br.com.sysaba.modules.relatorio.dto;

import java.util.UUID;

public class AnotacaoDTO {

    private UUID anotacaoId;
    private String data;
    private String descricao;

    public AnotacaoDTO() {
    }

    public AnotacaoDTO(UUID anotacaoId, String data, String descricao) {
        this.anotacaoId = anotacaoId;
        this.data = data;
        this.descricao = descricao;
    }

    public UUID getAnotacaoId() {
        return anotacaoId;
    }

    public void setAnotacaoId(UUID anotacaoId) {
        this.anotacaoId = anotacaoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
