package br.com.sysaba.modules.avaliacoes.portage.dto;

import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappColetaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class PortageColetaDTO {

    @JsonProperty("portage_uuid_fk")
    private UUID portageUuidFk;

    @JsonProperty("aprendiz_uuid_fk")
    private UUID aprendizUuidFk;

    @JsonProperty("idade_coleta")
    private String idadeColeta;

    @JsonProperty("resposta")
    private String resposta;

    @JsonProperty("coleta_id")
    private Integer coletaId;

    public PortageColetaDTO(){}

    public PortageColetaDTO(UUID portageUuidFk, UUID aprendizUuidFk, String idadeColeta, String resposta, Integer coletaId) {
        this.portageUuidFk = portageUuidFk;
        this.aprendizUuidFk = aprendizUuidFk;
        this.idadeColeta = idadeColeta;
        this.resposta = resposta;
        this.coletaId = coletaId;
    }

    public static PortageColetaDTO of(PortageColeta portageColeta) {
        PortageColetaDTO portageColetaDTO = new PortageColetaDTO();
        portageColetaDTO.setPortageUuidFk(portageColeta.getPortageColetaId());
        portageColetaDTO.setColetaId(portageColeta.getColetaId());
        portageColetaDTO.setIdadeColeta(portageColeta.getIdadeColeta());
        portageColetaDTO.setResposta(portageColeta.getResposta());
        portageColetaDTO.setAprendizUuidFk(portageColeta.getAprendiz().getAprendizId());
        return portageColetaDTO;
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

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Integer getColetaId() {
        return coletaId;
    }

    public void setColetaId(Integer coletaId) {
        this.coletaId = coletaId;
    }
}