package br.com.sysaba.modules.avaliacoes.vbmapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum VBMappNivelDoisEnum {
    IMITACAO(1, "Imitação"),
    ECOICO(2, "Ecoico EESA"),
    OUVINTE(3, "Ouvinte"),
    VPMTS(4, "VP-MTS"),
    MANDO(5, "Mando"),
    TATO(6, "Tato"),
    BRINCAR(7, "Brincar"),
    SOCIAL(8, "Social"),
    RESPOSTA(9, "Resposta - (LRFFC)"),
    INTERVERBAL(10, "Interverbal"),
    GRUPO(11, "Grupo"),
    LINGUISTICA(12, "Linguístisca"),
    ;

    private final Integer tipo;
    private final String descricao;
    ;
    private static final Map<Integer, VBMappNivelDoisEnum> lookup = new HashMap<>();

    static {
        for (VBMappNivelDoisEnum s : VBMappNivelDoisEnum.values()) {
            lookup.put(s.getTipo(), s);
        }
    }

    VBMappNivelDoisEnum(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public static VBMappNivelDoisEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
}
