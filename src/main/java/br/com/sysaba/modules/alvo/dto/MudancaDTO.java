package br.com.sysaba.modules.alvo.dto;

import java.util.UUID;

public class MudancaDTO {
    public UUID alvoId;
    public String tipo;
    public Integer quantidade;

    public MudancaDTO() {
    }

    public UUID getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(UUID alvoId) {
        this.alvoId = alvoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
