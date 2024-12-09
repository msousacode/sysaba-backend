package br.com.sysaba.modules.avaliacoes.vbmapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VbMappDTO {

    @JsonProperty("aprendiz_uuid_fk")
    private String aprendizUuidFk;

    @JsonProperty("nome_aprendiz")
    private String nomeAprendiz;

    @JsonProperty("objetivo_documento")
    private String objetivoDocumento;

    @JsonProperty("niveis_coleta")
    private String niveisColeta;

    private String protocolo;

    private boolean ativo;

    public VbMappDTO() {
    }

    public VbMappDTO(String aprendizUuidFk, String nomeAprendiz, String objetivoDocumento, String niveisColeta, String protocolo, boolean ativo) {
        this.aprendizUuidFk = aprendizUuidFk;
        this.nomeAprendiz = nomeAprendiz;
        this.objetivoDocumento = objetivoDocumento;
        this.niveisColeta = niveisColeta;
        this.protocolo = protocolo;
        this.ativo = ativo;
    }

    public String getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(String aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public String getObjetivoDocumento() {
        return objetivoDocumento;
    }

    public void setObjetivoDocumento(String objetivoDocumento) {
        this.objetivoDocumento = objetivoDocumento;
    }

    public String getNiveisColeta() {
        return niveisColeta;
    }

    public void setNiveisColeta(String niveisColeta) {
        this.niveisColeta = niveisColeta;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}