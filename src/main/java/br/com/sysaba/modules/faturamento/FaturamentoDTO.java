package br.com.sysaba.modules.faturamento;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.UUID;

public class FaturamentoDTO {

    private UUID faturamentoId;
    private UUID profissionalId;
    private String nomeProfissional;
    private String cargoProfissional;
    private String valorTotal;
    private Integer sessoesTotal;
    private Integer ausenciasJustificadasTotal;
    private Integer ausenciasNaoJustificadasTotal;
    private UUID aprendizId;
    private String nomeAprendiz;
    private Boolean ausenciaJustificada;
    private String descricaoJustificativa;
    private Boolean presente;
    private Integer dia;
    private String mes;
    private String mesDescricao;
    private String ano;
    private String situacao;

    // Campos auxiliares para apresentação ou filtros
    private String dataPorExtenso;
    private String valorFormatado;

    public static FaturamentoDTO of(Object[] object) {

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(localeBR);

        String valorFormatado = formatadorMoeda.format(object[2]);

        FaturamentoDTO dto = new FaturamentoDTO();
        dto.setAprendizId((UUID) object[0]);
        dto.setNomeAprendiz(String.valueOf(object[1]));
        dto.setValorTotal(valorFormatado);
        dto.setSituacao(String.valueOf(object[3]));
        dto.setMes(String.valueOf(object[4]));
        dto.setMesDescricao(getMesDescricao(String.valueOf(object[4])));
        dto.setAno(String.valueOf(object[5]));

        return dto;
    }

    private static String getMesDescricao(String mes) {
        if (mes == null) {
            throw new IllegalArgumentException("Mês não pode ser null");
        }

        // Remove zeros à esquerda se existirem
        mes = mes.replaceFirst("^0+", "");

        switch (mes) {
            case "1":
                return "Janeiro";
            case "2":
                return "Fevereiro";
            case "3":
                return "Março";
            case "4":
                return "Abril";
            case "5":
                return "Maio";
            case "6":
                return "Junho";
            case "7":
                return "Julho";
            case "8":
                return "Agosto";
            case "9":
                return "Setembro";
            case "10":
                return "Outubro";
            case "11":
                return "Novembro";
            case "12":
                return "Dezembro";
            default:
                throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }

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

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
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

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
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

    public Integer getSessoesTotal() {
        return sessoesTotal;
    }

    public void setSessoesTotal(Integer sessoesTotal) {
        this.sessoesTotal = sessoesTotal;
    }

    public Integer getAusenciasJustificadasTotal() {
        return ausenciasJustificadasTotal;
    }

    public void setAusenciasJustificadasTotal(Integer ausenciasJustificadasTotal) {
        this.ausenciasJustificadasTotal = ausenciasJustificadasTotal;
    }

    public Integer getAusenciasNaoJustificadasTotal() {
        return ausenciasNaoJustificadasTotal;
    }

    public void setAusenciasNaoJustificadasTotal(Integer ausenciasNaoJustificadasTotal) {
        this.ausenciasNaoJustificadasTotal = ausenciasNaoJustificadasTotal;
    }

    public String getMesDescricao() {
        return mesDescricao;
    }

    public void setMesDescricao(String mesDescricao) {
        this.mesDescricao = mesDescricao;
    }
}
