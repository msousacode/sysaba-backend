package br.com.sysaba.modules.avaliacoes.portage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PortageDTO {

    @JsonProperty("aprendiz_uuid_fk")
    private UUID aprendizId;

    @JsonProperty("idades_coleta")
    private String idadesColeta;

    @JsonProperty("objetivo_documento")
    private String objetivo;

    @JsonProperty("protocolo")
    private String protocolo;

    public PortageDTO() {
    }

    public PortageDTO(UUID aprendizId, String idadesColeta, String objetivo, String protocolo) {
        this.aprendizId = aprendizId;
        this.idadesColeta = idadesColeta;
        this.objetivo = objetivo;
        this.protocolo = protocolo;
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getIdadesColeta() {
        return idadesColeta;
    }

    public void setIdadesColeta(String idadesColeta) {
        this.idadesColeta = idadesColeta;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}