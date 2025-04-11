package br.com.sysaba.modules.faturamento;

import java.text.NumberFormat;
import java.util.Locale;

public class FaturamentoSumarioDTO {
    private Integer totalAprendizes;
    private Integer totalSessoesRealizadas;
    private Integer totalAusenciasJustificadas;
    private Integer totalAusenciasNaoJustificadas;
    private String valorTotalPendentes;
    private String valorTotalPagos;
    private String faturamentoTotalMes;

    // Construtores
    public FaturamentoSumarioDTO() {
    }

    public FaturamentoSumarioDTO(Integer totalAprendizes, Integer totalSessoesRealizadas, Integer totalAusenciasJustificadas, Integer totalAusenciasNaoJustificadas, String valorTotalPendentes, String valorTotalPagos, String faturamentoTotalMes) {
        this.totalAprendizes = totalAprendizes;
        this.totalSessoesRealizadas = totalSessoesRealizadas;
        this.totalAusenciasJustificadas = totalAusenciasJustificadas;
        this.totalAusenciasNaoJustificadas = totalAusenciasNaoJustificadas;
        this.valorTotalPendentes = valorTotalPendentes;
        this.valorTotalPagos = valorTotalPagos;
        this.faturamentoTotalMes = faturamentoTotalMes;
    }

    public static FaturamentoSumarioDTO of(Object[] object) {

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(localeBR);

        String valorFormatado = formatadorMoeda.format(object[0]);

        FaturamentoSumarioDTO dto = new FaturamentoSumarioDTO();

        dto.setFaturamentoTotalMes(valorFormatado);
        return dto;
    }

    public Integer getTotalAprendizes() {
        return totalAprendizes;
    }

    public void setTotalAprendizes(Integer totalAprendizes) {
        this.totalAprendizes = totalAprendizes;
    }

    public Integer getTotalSessoesRealizadas() {
        return totalSessoesRealizadas;
    }

    public void setTotalSessoesRealizadas(Integer totalSessoesRealizadas) {
        this.totalSessoesRealizadas = totalSessoesRealizadas;
    }

    public Integer getTotalAusenciasJustificadas() {
        return totalAusenciasJustificadas;
    }

    public void setTotalAusenciasJustificadas(Integer totalAusenciasJustificadas) {
        this.totalAusenciasJustificadas = totalAusenciasJustificadas;
    }

    public Integer getTotalAusenciasNaoJustificadas() {
        return totalAusenciasNaoJustificadas;
    }

    public void setTotalAusenciasNaoJustificadas(Integer totalAusenciasNaoJustificadas) {
        this.totalAusenciasNaoJustificadas = totalAusenciasNaoJustificadas;
    }

    public String getValorTotalPendentes() {
        return valorTotalPendentes;
    }

    public void setValorTotalPendentes(String valorTotalPendentes) {
        this.valorTotalPendentes = valorTotalPendentes;
    }

    public String getValorTotalPagos() {
        return valorTotalPagos;
    }

    public void setValorTotalPagos(String valorTotalPagos) {
        this.valorTotalPagos = valorTotalPagos;
    }

    public String getFaturamentoTotalMes() {
        return faturamentoTotalMes;
    }

    public void setFaturamentoTotalMes(String faturamentoTotalMes) {
        this.faturamentoTotalMes = faturamentoTotalMes;
    }
}
