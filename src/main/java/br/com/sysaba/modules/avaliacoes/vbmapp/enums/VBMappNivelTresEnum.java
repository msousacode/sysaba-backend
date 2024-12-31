package br.com.sysaba.modules.avaliacoes.vbmapp.enums;

import java.util.HashMap;
import java.util.Map;

public enum VBMappNivelTresEnum {
    MANDO(1, "Mando"),
    TATO(2, "Tato"),
    OUVINTE(3, "Ouvinte"),
    VPMTS(4, "VP-MTS"),
    BRINCAR(5, "Brincar"),
    SOCIAL(6, "Social"),
    LEITURA(7, "Leitura"),
    ESCRITA(8, "Escrita"),
    LRFFC(9, "LRFFC"),
    INTERVERBAL(10, "Interverbal"),
    VOCAL(11, "Vocal"),
    GRUPO(12, "Grupo"),
    LINGUISTICA(13, "Linguística"),
    MATEMATICA(14, "Matemática"),
    ;

    private final Integer tipo;
    private final String descricao;
    ;
    private static final Map<Integer, VBMappNivelTresEnum> lookup = new HashMap<>();

    static {
        for (VBMappNivelTresEnum s : VBMappNivelTresEnum.values()) {
            lookup.put(s.getTipo(), s);
        }
    }

    VBMappNivelTresEnum(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public static VBMappNivelTresEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getTipo() {
        return tipo;
    }
}
