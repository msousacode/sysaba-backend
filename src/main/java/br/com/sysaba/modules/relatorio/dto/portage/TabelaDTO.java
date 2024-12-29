package br.com.sysaba.modules.relatorio.dto.portage;

public class TabelaDTO {

    private String faixaEtaria;
    private Double pontuacaoAtingida;
    private int pontuacaoEsperada;
    private int tipo;

    public TabelaDTO() {
    }

    public TabelaDTO(String faixaEtaria, Double pontuacaoAtingida, int pontuacaoEsperada, int tipo) {
        this.faixaEtaria = faixaEtaria;
        this.pontuacaoAtingida = pontuacaoAtingida;
        this.pontuacaoEsperada = pontuacaoEsperada;
        this.tipo = tipo;
    }

    public String getFaixaEtaria() {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public Double getPontuacaoAtingida() {
        return pontuacaoAtingida;
    }

    public void setPontuacaoAtingida(Double pontuacaoAtingida) {
        this.pontuacaoAtingida = pontuacaoAtingida;
    }

    public int getPontuacaoEsperada() {
        return pontuacaoEsperada;
    }

    public void setPontuacaoEsperada(int pontuacaoEsperada) {
        this.pontuacaoEsperada = pontuacaoEsperada;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
