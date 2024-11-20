package br.com.sysaba.modules.atendimento.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.UUID;

public class TreinamentoItemDTO {

    @JsonAlias({"treinamentoId", "uuid"})
    private UUID treinamentoId;

    private String treinamento;

    private String protocolo;

    private Boolean ativo;

    private ConfiguracoesDTO configuracoes;

    public TreinamentoItemDTO() {
    }

    public TreinamentoItemDTO(UUID treinamentoId, String treinamento, String protocolo, Boolean ativo, ConfiguracoesDTO configuracoesDTO) {
        this.treinamentoId = treinamentoId;
        this.treinamento = treinamento;
        this.protocolo = protocolo;
        this.ativo = ativo;
        this.configuracoes = configuracoesDTO;
    }

    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = treinamento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }


    public ConfiguracoesDTO getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ConfiguracoesDTO configuracoes) {
        this.configuracoes = configuracoes;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }
}