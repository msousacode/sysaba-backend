package br.com.sysaba.modules.alvo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AlvoDTO {

    private String alvoId;

    @JsonProperty("nome_alvo")
    private String nomeAlvo;

    private String pergunta;

    @JsonProperty("descricao_alvo")
    private String descricaoAlvo;

    @JsonProperty("treinamento_uuid_fk")
    private String treinamentoUuidFk;

    @JsonProperty("tipo_aprendizado")
    private String tipoAprendizado;

    @JsonProperty("identificador")
    private UUID identificador = UUID.randomUUID();

    // Construtor padrão
    public AlvoDTO() {
    }

    public String getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(String alvoId) {
        this.alvoId = alvoId;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDescricaoAlvo() {
        return descricaoAlvo;
    }

    public void setDescricaoAlvo(String descricaoAlvo) {
        this.descricaoAlvo = descricaoAlvo;
    }

    public String getTreinamentoUuidFk() {
        return treinamentoUuidFk;
    }

    public void setTreinamentoUuidFk(String treinamentoUuidFk) {
        this.treinamentoUuidFk = treinamentoUuidFk;
    }

    public String getTipoAprendizado() {
        return tipoAprendizado;
    }

    public void setTipoAprendizado(String tipoAprendizado) {
        this.tipoAprendizado = tipoAprendizado;
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public void setIdentificador(UUID identificador) {
        this.identificador = identificador;
    }
}
