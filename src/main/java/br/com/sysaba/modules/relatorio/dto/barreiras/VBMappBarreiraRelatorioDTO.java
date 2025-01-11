package br.com.sysaba.modules.relatorio.dto.barreiras;

import br.com.sysaba.modules.relatorio.dto.portage.CabecalhoDTO;

import java.util.List;

public class VBMappBarreiraRelatorioDTO {

    private String titulo;

    private CabecalhoDTO cabecalhoDTO;

    private String chartImg;

    private List<TabelaBarreiraDTO> tabela;

    public CabecalhoDTO getCabecalhoDTO() {
        return cabecalhoDTO;
    }

    public void setCabecalhoDTO(CabecalhoDTO cabecalhoDTO) {
        this.cabecalhoDTO = cabecalhoDTO;
    }

    public String getChartImg() {
        return chartImg;
    }

    public void setChartImg(String chartImg) {
        this.chartImg = chartImg;
    }

    public List<TabelaBarreiraDTO> getTabela() {
        return tabela;
    }

    public void setTabela(List<TabelaBarreiraDTO> tabela) {
        this.tabela = tabela;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
