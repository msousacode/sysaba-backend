package br.com.sysaba.modules.avaliacoes.portage.enums;

import java.util.HashMap;
import java.util.Map;

public enum PortageAvaliacaoEnum {
    SOCIALIZACAO(1, "Socilização"),
    COGNICAO(2, "Cognição"),
    LINGUAGEM(3, "Linguagem"),
    AUTOCUIDADO(4, "Autocuidados"),
    MOTOR(5, "Motor"),
    ;

    private final Integer cod;
    private final String descricao;
    private static final Map<Integer, PortageAvaliacaoEnum> lookup = new HashMap<>();

    static {
        for (PortageAvaliacaoEnum s : PortageAvaliacaoEnum.values()) {
            lookup.put(s.getCod(), s);
        }
    }

    PortageAvaliacaoEnum(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static PortageAvaliacaoEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCod() {
        return cod;
    }
}
