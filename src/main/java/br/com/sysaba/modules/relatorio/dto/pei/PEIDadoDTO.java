package br.com.sysaba.modules.relatorio.dto.pei;

import java.util.List;

public class PEIDadoDTO {
    private String titulo;
    private List<PEIObjetivoDTO> objetivosZero;
    private List<PEIObjetivoDTO> objetivosMeio;

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<PEIObjetivoDTO> getObjetivosZero() {
        return objetivosZero;
    }

    public void setObjetivosZero(List<PEIObjetivoDTO> objetivosZero) {
        this.objetivosZero = objetivosZero;
    }

    public List<PEIObjetivoDTO> getObjetivosMeio() {
        return objetivosMeio;
    }

    public void setObjetivosMeio(List<PEIObjetivoDTO> objetivosMeio) {
        this.objetivosMeio = objetivosMeio;
    }
}
