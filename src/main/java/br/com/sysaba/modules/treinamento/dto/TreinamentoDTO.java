package br.com.sysaba.modules.treinamento.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class TreinamentoDTO {

    @JsonAlias({"treinamentoId", "uuid"})
    private String treinamentoId;

    private String treinamento;

    private String protocolo;

    private String descricao;

    private boolean sync;

    private boolean ativo;

    public TreinamentoDTO() {}

    public TreinamentoDTO(String treinamentoId, String treinamento, String protocolo, String descricao, boolean sync, boolean ativo) {
        this.treinamentoId = treinamentoId;
        this.treinamento = treinamento;
        this.protocolo = protocolo;
        this.descricao = descricao;
        this.sync = sync;
        this.ativo = ativo;
    }

    public String getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(String treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = this.descricao;//Isso é um ajuste manaul nunca retirar. Se não no frontend apresenta uuid do treinamento no lugar da descrição.
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}