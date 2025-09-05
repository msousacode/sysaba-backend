package br.com.sysaba.modules.alvo.dto;

import java.util.List;
import java.util.UUID;

public class AlvoImportDTO {
    private UUID aprendizId;

    private UUID alvoId;
    private String nomeAlvo;
    private String tag;
    private boolean concluido;
    private boolean encerrado;
    private Integer totalEstrelaPositiva;
    private Integer totalEstrelaNegativa;
    private UUID alvoPaiId;

    private List<UUID> objetivos;

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public List<UUID> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<UUID> objetivos) {
        this.objetivos = objetivos;
    }

    public UUID getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(UUID alvoId) {
        this.alvoId = alvoId;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public Integer getTotalEstrelaPositiva() {
        return totalEstrelaPositiva;
    }

    public void setTotalEstrelaPositiva(Integer totalEstrelaPositiva) {
        this.totalEstrelaPositiva = totalEstrelaPositiva;
    }

    public Integer getTotalEstrelaNegativa() {
        return totalEstrelaNegativa;
    }

    public void setTotalEstrelaNegativa(Integer totalEstrelaNegativa) {
        this.totalEstrelaNegativa = totalEstrelaNegativa;
    }

    public UUID getAlvoPaiId() {
        return alvoPaiId;
    }

    public void setAlvoPaiId(UUID alvoPaiId) {
        this.alvoPaiId = alvoPaiId;
    }    
}
