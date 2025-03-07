package br.com.sysaba.modules.treinamento.dto;

import java.util.UUID;

public class TreinamentoBaseDTO {

    private UUID treinamentoBaseId;
    private String titulo;
    private String nomeAlvo;
    private String descricao;
    private String protocolo;
    private String habilidade;
    private Boolean isImportado = false;
    private UUID importId;

    public TreinamentoBaseDTO() {
    }

    public TreinamentoBaseDTO(UUID treinamentoBaseId, String titulo, String descricao, String protocolo, String habilidade, Boolean isImportado) {
        this.treinamentoBaseId = treinamentoBaseId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.protocolo = protocolo;
        this.habilidade = habilidade;
        this.isImportado = isImportado;
    }

    public UUID getTreinamentoBaseId() {
        return treinamentoBaseId;
    }

    public void setTreinamentoBaseId(UUID treinamentoBaseId) {
        this.treinamentoBaseId = treinamentoBaseId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public Boolean getImportado() {
        return isImportado;
    }

    public void setImportado(Boolean importado) {
        isImportado = importado;
    }

    public UUID getImportId() {
        return importId;
    }

    public void setImportId(UUID importId) {
        this.importId = importId;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }
}
