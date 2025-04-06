package br.com.sysaba.modules.faturamento;

import java.util.UUID;

public class FaturamentoDTO {

    private UUID faturamentoId;
    private UUID profissionalId;
    private String nomeProfissional;
    private String cargoProfissional;
    private String preco;
    private UUID aprendizId;
    private String nomeAprendiz;
    private Boolean ausenciaJustificada;
    private String descricaoJustificativa;
    private Boolean presente;
    private Integer dia;
    private Integer mes;
    private Integer ano;
    private PagamentoEnum situacao;

    // Campos auxiliares para apresentação ou filtros
    private String dataPorExtenso;
    private String valorFormatado;

    /**
     * Método para obter a data no formato DD/MM/AAAA
     *
     * @return String contendo a data formatada
     */
    public String getDataFormatada() {
        if (dia != null && mes != null && ano != null) {
            return String.format("%02d/%02d/%04d", dia, mes, ano);
        }
        return null;
    }

    /**
     * Verifica se o registro representa uma ausência
     *
     * @return true se for uma ausência, false caso contrário
     */
    public Boolean isAusencia() {
        return presente != null && !presente;
    }

    /**
     * Método para calcular se um pagamento está pendente
     *
     * @return true se o pagamento está pendente, false caso contrário
     */
    public Boolean isPagamentoPendente() {
        return PagamentoEnum.PENDENTE.equals(situacao);
    }

    public UUID getFaturamentoId() {
        return faturamentoId;
    }

    public void setFaturamentoId(UUID faturamentoId) {
        this.faturamentoId = faturamentoId;
    }

    public UUID getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(UUID profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getCargoProfissional() {
        return cargoProfissional;
    }

    public void setCargoProfissional(String cargoProfissional) {
        this.cargoProfissional = cargoProfissional;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public Boolean getAusenciaJustificada() {
        return ausenciaJustificada;
    }

    public void setAusenciaJustificada(Boolean ausenciaJustificada) {
        this.ausenciaJustificada = ausenciaJustificada;
    }

    public String getDescricaoJustificativa() {
        return descricaoJustificativa;
    }

    public void setDescricaoJustificativa(String descricaoJustificativa) {
        this.descricaoJustificativa = descricaoJustificativa;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public PagamentoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(PagamentoEnum situacao) {
        this.situacao = situacao;
    }

    public String getDataPorExtenso() {
        return dataPorExtenso;
    }

    public void setDataPorExtenso(String dataPorExtenso) {
        this.dataPorExtenso = dataPorExtenso;
    }

    public String getValorFormatado() {
        return valorFormatado;
    }

    public void setValorFormatado(String valorFormatado) {
        this.valorFormatado = valorFormatado;
    }
}
