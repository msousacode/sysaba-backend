package br.com.sysaba.modules.relatorio.dto.pei;

import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;

public class PEIObjetivoDTO {
    private String cod;
    private String descricao;
    private Integer tipo;

    public PEIObjetivoDTO(String cod, String descricao, Integer tipo) {
        this.cod = cod;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public static PEIObjetivoDTO of(PortageColeta coleta) {
        return new PEIObjetivoDTO(String.valueOf(coleta.getCodigo()), coleta.getDescricao(), coleta.getTipo());
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
