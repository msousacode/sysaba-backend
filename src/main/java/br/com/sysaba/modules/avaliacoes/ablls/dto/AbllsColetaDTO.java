package br.com.sysaba.modules.avaliacoes.ablls.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbllsColetaDTO {

    @JsonProperty("aprendiz_uuid_fk")
    private String aprendizUuidFk;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("id")
    private int id;

    @JsonProperty("resposta")
    private int resposta;

    // Getters e Setters

    public String getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(String aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }
}
