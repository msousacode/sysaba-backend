package br.com.sysaba.modules.avaliacoes.portage.enums;

import java.util.HashMap;
import java.util.Map;

public enum PortageFaixaEnum {
    ZERO_A_UM_ANO(1, "0 a 1 ano"),
    UM_A_DOIS_ANOS(2, "1 a 2 anos"),
    DOIS_A_TRES_ANOS(3, "2 a 3 anos"),
    TRES_A_QUATRO_ANOS(4, "3 a 4 anos"),
    QUATRO_A_CINCO_ANOS(5, "4 a 5 anos"),
    CINCO_A_SEIS_ANOS(6, "5 a 6 anos"),
    ;

    private final Integer cod;
    private final String descricao;
    private static final Map<Integer, PortageFaixaEnum> lookup = new HashMap<>();

    static {
        for (PortageFaixaEnum s : PortageFaixaEnum.values()) {
            lookup.put(s.getCod(), s);
        }
    }

    PortageFaixaEnum(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static PortageFaixaEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCod() {
        return cod;
    }
}
