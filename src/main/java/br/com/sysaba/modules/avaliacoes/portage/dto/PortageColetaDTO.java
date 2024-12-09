package br.com.sysaba.modules.avaliacoes.portage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PortageColetaDTO {

    @JsonProperty("portage_uuid_fk")
    private UUID portageUuidFk;

    @JsonProperty("aprendiz_uuid_fk")
    private UUID aprendizUuidFk;

    @JsonProperty("idade_coleta")
    private String idadeColeta;

    @JsonProperty("codigo")
    private int codigo;

    @JsonProperty("resposta")
    private String resposta;

    public PortageColetaDTO(){}

    public PortageColetaDTO(UUID portageUuidFk, UUID aprendizUuidFk, String idadeColeta, int codigo, String resposta) {
        this.portageUuidFk = portageUuidFk;
        this.aprendizUuidFk = aprendizUuidFk;
        this.idadeColeta = idadeColeta;
        this.codigo = codigo;
        this.resposta = resposta;
    }

    public UUID getPortageUuidFk() {
        return portageUuidFk;
    }

    public void setPortageUuidFk(UUID portageUuidFk) {
        this.portageUuidFk = portageUuidFk;
    }

    public UUID getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(UUID aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public String getIdadeColeta() {
        return idadeColeta;
    }

    public void setIdadeColeta(String idadeColeta) {
        this.idadeColeta = idadeColeta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}