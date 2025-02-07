package br.com.sysaba.modules.aprendiz.dto;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AprendizDTO {

    @JsonProperty("uuid")
    private UUID aprendizId;

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

    @JsonProperty("ativo")
    private boolean ativo;

    private List<String> profissionais = new ArrayList<>();

    // Construtor padrão
    public AprendizDTO() {
    }

    public static AprendizDTO fromAprendizDTO(Aprendiz aprendiz) {
        AprendizDTO dto = new AprendizDTO();

        return dto;
    }

    // Getters e Setters

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

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<String> getProfissionais() {
        return profissionais;
    }

    public void setProfissionais(List<String> profissionais) {
        this.profissionais = profissionais;
    }
}
