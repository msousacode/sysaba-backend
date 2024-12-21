package br.com.sysaba.modules.relatorio.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TreinamentoDTO {

    private UUID treinamentoId;
    private String data;
    private String titulo;
    private String protocolo;
    private String descricao;
    private List<AlvoColetadoDTO> alvosColetados;
    private List<ProfissionalDTO> profissionalDTOS;
    private String chartImage;
    private List<String> chartImageList;

    public TreinamentoDTO() {
        this.chartImageList = new ArrayList<>();
    }

    public TreinamentoDTO(UUID treinamentoId, String data, String titulo, String protocolo, String descricao, List<AlvoColetadoDTO> alvosColetados, List<ProfissionalDTO> profissionalDTOS, String chartImage, List<String> chartImageList) {
        this.treinamentoId = treinamentoId;
        this.data = data;
        this.titulo = titulo;
        this.protocolo = protocolo;
        this.descricao = descricao;
        this.alvosColetados = alvosColetados;
        this.profissionalDTOS = profissionalDTOS;
        this.chartImage = chartImage;
        this.chartImageList = chartImageList;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<AlvoColetadoDTO> getAlvosColetados() {
        return alvosColetados;
    }

    public void setAlvosColetados(List<AlvoColetadoDTO> alvosColetados) {
        this.alvosColetados = alvosColetados;
    }

    public List<ProfissionalDTO> getProfissionalDTOS() {
        return profissionalDTOS;
    }

    public void setProfissionalDTOS(List<ProfissionalDTO> profissionalDTOS) {
        this.profissionalDTOS = profissionalDTOS;
    }

    public String getChartImage() {
        return chartImage;
    }

    public void setChartImage(String chartImage) {
        this.chartImage = chartImage;
    }

    public List<String> getChartImageList() {
        return chartImageList;
    }

    public void setChartImageList(List<String> chartImageList) {
        this.chartImageList = chartImageList;
    }
}
