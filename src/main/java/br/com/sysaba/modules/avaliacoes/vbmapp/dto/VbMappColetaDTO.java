package br.com.sysaba.modules.avaliacoes.vbmapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

public class VbMappColetaDTO {

    @JsonProperty("vbmapp_uuid_fk")
    private UUID vbmappUuidFk;

    @JsonProperty("aprendiz_uuid_fk")
    private UUID aprendizUuidFk;

    @JsonProperty("coleta_id")
    private int coletaId;

    @JsonProperty("nivel_coleta")
    private int nivelColeta;

    @JsonProperty("tipo")
    private int tipo;

    @JsonProperty("pontuacao")
    private double pontuacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("data_coleta")
    private LocalDate dataColeta;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("criado_por_nome")
    private String criadoNome;

    public VbMappColetaDTO() {
    }

    public VbMappColetaDTO(UUID vbmappUuidFk, UUID aprendizUuidFk, int coletaId, int nivelColeta, int tipo, double pontuacao, LocalDate dataColeta) {
        this.vbmappUuidFk = vbmappUuidFk;
        this.aprendizUuidFk = aprendizUuidFk;
        this.coletaId = coletaId;
        this.nivelColeta = nivelColeta;
        this.tipo = tipo;
        this.pontuacao = pontuacao;
        this.dataColeta = dataColeta;
    }

    public UUID getVbmappUuidFk() {
        return vbmappUuidFk;
    }

    public void setVbmappUuidFk(UUID vbmappUuidFk) {
        this.vbmappUuidFk = vbmappUuidFk;
    }

    public UUID getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(UUID aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public int getColetaId() {
        return coletaId;
    }

    public void setColetaId(int coletaId) {
        this.coletaId = coletaId;
    }

    public int getNivelColeta() {
        return nivelColeta;
    }

    public void setNivelColeta(int nivelColeta) {
        this.nivelColeta = nivelColeta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCriadoNome() {
        return criadoNome;
    }

    public void setCriadoNome(String criadoNome) {
        this.criadoNome = criadoNome;
    }
}