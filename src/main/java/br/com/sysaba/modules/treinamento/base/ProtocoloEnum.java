package br.com.sysaba.modules.treinamento.base;

public enum ProtocoloEnum {
    ABC(1, "ABC"),
    OCORRENCIA_RESPOSTA(2, "Ocorrência de Resposta");

    private Integer codigo;
    private String descricao;

    ProtocoloEnum(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
