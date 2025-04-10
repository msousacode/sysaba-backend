package br.com.sysaba.modules.faturamento;

import java.util.UUID;

public class FaturamentoBuscarDTO {

    private UUID aprendizId;
    private String nomeAprendiz;
    private Integer mesReferencia;
    private Integer anoReferencia;
    private Integer totalTerapiasRealizadas;
    private Integer totalFaltasJustificadas;
    private Integer totalFaltasNaoJustificadas;
    private String total;
    private PagamentoEnum situacao;

    // Construtores
    public FaturamentoBuscarDTO(Object[] result) {
    }

    public FaturamentoBuscarDTO(UUID aprendizId, String nomeAprendiz, Integer mesReferencia,
                                Integer anoReferencia, Integer totalTerapiasRealizadas,
                                Integer totalFaltasJustificadas, Integer totalFaltasNaoJustificadas,
                                String total, PagamentoEnum situacao) {
        this.aprendizId = aprendizId;
        this.nomeAprendiz = nomeAprendiz;
        this.mesReferencia = mesReferencia;
        this.anoReferencia = anoReferencia;
        this.totalTerapiasRealizadas = totalTerapiasRealizadas;
        this.totalFaltasJustificadas = totalFaltasJustificadas;
        this.totalFaltasNaoJustificadas = totalFaltasNaoJustificadas;
        this.total = total;
        this.situacao = situacao;
    }

    public static FaturamentoBuscarDTO of(
            FaturamentoGeral faturamentoGeral,
            Integer totalTerapiasRealizadas,
            Integer totalFaltasJustificadas,
            Integer totalFaltasNaoJustificadas,
            String total) {
        //@format:on
        return new FaturamentoBuscarDTO(
                faturamentoGeral.getAprendizId(),
                faturamentoGeral.getNomeAprendiz(),
                faturamentoGeral.getMes(),
                faturamentoGeral.getAno(),
                totalTerapiasRealizadas,
                totalFaltasJustificadas,
                totalFaltasNaoJustificadas,
                total,
                faturamentoGeral.getSituacao()

        );
        //@format:off
    }


    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public Integer getMesReferencia() {
        return mesReferencia;
    }

    public void setMesReferencia(Integer mesReferencia) {
        this.mesReferencia = mesReferencia;
    }

    public Integer getAnoReferencia() {
        return anoReferencia;
    }

    public void setAnoReferencia(Integer anoReferencia) {
        this.anoReferencia = anoReferencia;
    }

    public Integer getTotalTerapiasRealizadas() {
        return totalTerapiasRealizadas;
    }

    public void setTotalTerapiasRealizadas(Integer totalTerapiasRealizadas) {
        this.totalTerapiasRealizadas = totalTerapiasRealizadas;
    }

    public Integer getTotalFaltasJustificadas() {
        return totalFaltasJustificadas;
    }

    public void setTotalFaltasJustificadas(Integer totalFaltasJustificadas) {
        this.totalFaltasJustificadas = totalFaltasJustificadas;
    }

    public Integer getTotalFaltasNaoJustificadas() {
        return totalFaltasNaoJustificadas;
    }

    public void setTotalFaltasNaoJustificadas(Integer totalFaltasNaoJustificadas) {
        this.totalFaltasNaoJustificadas = totalFaltasNaoJustificadas;
    }

    // Métodos auxiliares
   /* public String getNomeMesReferencia() {
        if (mesReferencia != null && mesReferencia >= 1 && mesReferencia <= 12) {
            return Month.of(mesReferencia).getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
        }
        return null;
    }*/

    public Integer getTotalFaltas() {
        return (totalFaltasJustificadas != null ? totalFaltasJustificadas : 0) +
                (totalFaltasNaoJustificadas != null ? totalFaltasNaoJustificadas : 0);
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public PagamentoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(PagamentoEnum situacao) {
        this.situacao = situacao;
    }
}
