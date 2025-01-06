package br.com.sysaba.modules.avaliacoes.vbmapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum VBMappNivelUmEnum {
    IMITACAO(1, "Imitação"),
    ECOICO(2, "Ecoico (SUBTESTE APCE)"),
    OUVINTE(3, "Ouvinte"),
    VPMTS(4, "VP-MTS"),
    MANDO(5, "Mando"),
    TATO(6, "Tato"),
    BRINCAR(7, "Brincar"),
    SOCIAL(8, "Social"),
    VOCAL(9, "Vocal"),
    ;

    private final Integer tipo;
    private final String descricao;
    ;
    private static final Map<Integer, VBMappNivelUmEnum> lookup = new HashMap<>();

    static {
        for (VBMappNivelUmEnum s : VBMappNivelUmEnum.values()) {
            lookup.put(s.getTipo(), s);
        }
    }

    VBMappNivelUmEnum(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public static VBMappNivelUmEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
}
