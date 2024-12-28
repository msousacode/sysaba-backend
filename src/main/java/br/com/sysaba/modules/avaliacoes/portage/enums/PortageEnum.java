package br.com.sysaba.modules.avaliacoes.portage.enums;

import java.util.HashMap;
import java.util.Map;

public enum PortageEnum {
    ZERO_A_UM_ANO(1, "Zero a um Ano"),
    UM_A_DOIS_ANOS(2, "Dois a três anos"),
    DOIS_A_TRES_ANOS(3, "Zero a Um anos"),
    TRES_A_QUATRO_ANOS(4, "Zero a Um anos"),
    QUATRO_A_CINCO_ANOS(5, "Zero a Um anos"),
    CINCO_A_SEIS_ANOS(6, "Zero a Um anos"),
    ;

    private final Integer cod;
    private final String descricao;
    private static final Map<Integer, PortageEnum> lookup = new HashMap<>();

    static {
        for (PortageEnum s : PortageEnum.values()) {
            lookup.put(s.getCod(), s);
        }
    }

    PortageEnum(Integer cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public static PortageEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCod() {
        return cod;
    }
}
