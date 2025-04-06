package br.com.sysaba.modules.faturamento;

public enum PagamentoEnum {
    PAGO("PAGO"),
    PENDENTE("PENDENTE");

    private String situacao;

    PagamentoEnum(String situacao) {
        this.situacao = situacao;
    }
}
