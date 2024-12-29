package br.com.sysaba.modules.relatorio.dto.portage;

import java.util.List;

public class PortageRelatorioDTO {
    private String titulo;
    private CabecalhoDTO cabecalho;
    private String chart;
    private List<DadosDTO> dados;

    public PortageRelatorioDTO() {
    }

    public PortageRelatorioDTO(String titulo, CabecalhoDTO cabecalho, String chart, List<DadosDTO> dados) {
        this.titulo = titulo;
        this.cabecalho = cabecalho;
        this.chart = chart;
        this.dados = dados;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CabecalhoDTO getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(CabecalhoDTO cabecalho) {
        this.cabecalho = cabecalho;
    }

    public String getChart() {
        return chart;
    }

    public void setChart(String chart) {
        this.chart = chart;
    }

    public List<DadosDTO> getDados() {
        return dados;
    }

    public void setDados(List<DadosDTO> dados) {
        this.dados = dados;
    }
}
