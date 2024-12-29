package br.com.sysaba.modules.relatorio.dto.portage;

import java.util.List;

public class DadosDTO {
    private String analise;
    private int tipo;
    private List<TabelaDTO> tabela;
    private double totalMediaIdade;

    public DadosDTO() {
    }

    public DadosDTO(String analise, Integer tipo, List<TabelaDTO> tabela, Double totalMediaIdade) {
        this.analise = analise;
        this.tipo = tipo;
        this.tabela = tabela;
        this.totalMediaIdade = totalMediaIdade;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public List<TabelaDTO> getTabela() {
        return tabela;
    }

    public void setTabela(List<TabelaDTO> tabela) {
        this.tabela = tabela;
    }

    public double getTotalMediaIdade() {
        return totalMediaIdade;
    }

    public void setTotalMediaIdade(double totalMediaIdade) {
        this.totalMediaIdade = totalMediaIdade;
    }
}
