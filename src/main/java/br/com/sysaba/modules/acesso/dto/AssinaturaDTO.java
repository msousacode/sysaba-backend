package br.com.sysaba.modules.acesso.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class AssinaturaDTO {

    @JsonProperty("assinatura_id")
    private UUID assinaturaId;

    @JsonProperty("tipo_assinatura")
    private String tipoAssinatura;

    @JsonProperty("data_contratacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataContratacao;

    public AssinaturaDTO() {
    }

    public AssinaturaDTO(UUID assinaturaId, String tipoAssinatura, LocalDateTime dataContratacao, Boolean ativo) {
        this.assinaturaId = assinaturaId;
        this.tipoAssinatura = tipoAssinatura;
        this.dataContratacao = dataContratacao;
        this.ativo = ativo;
    }

    private Boolean ativo;

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getAssinaturaId() {
        return assinaturaId;
    }

    public void setAssinaturaId(UUID assinaturaId) {
        this.assinaturaId = assinaturaId;
    }

    public String getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(String tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public LocalDateTime getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDateTime dataContratacao) {
        this.dataContratacao = dataContratacao;
    }
}
