package br.com.sysaba.modules.aprendiz.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class AprendizDTO {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("nome_aprendiz")
    private String nomeAprendiz;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("nasc_aprendiz")
    private LocalDate nascAprendiz;

    @JsonProperty("nome_mae")
    private String nomeMae;

    @JsonProperty("nome_pai")
    private String nomePai;

    @JsonProperty("nome_responsavel")
    private String nomeResponsavel;

    @JsonProperty("observacao")
    private String observacao;

    // Construtor padrão
    public AprendizDTO() {
    }

    // Getters e Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public LocalDate getNascAprendiz() {
        return nascAprendiz;
    }

    public void setNascAprendiz(LocalDate nascAprendiz) {
        this.nascAprendiz = nascAprendiz;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
