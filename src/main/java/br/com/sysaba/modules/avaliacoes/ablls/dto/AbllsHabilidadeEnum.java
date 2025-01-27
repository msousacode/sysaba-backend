package br.com.sysaba.modules.avaliacoes.ablls.dto;

import br.com.sysaba.modules.avaliacoes.vbmapp.enums.VBMappNivelUmEnum;

import java.util.HashMap;
import java.util.Map;

public enum AbllsHabilidadeEnum {
    COOPERACAO_E_EFICACIA_DO_REFORCADOR(1, "Cooperação e Eficácia do Reforçador"),
    DESEMPENHO_VISUAL(2, "Desempenho Visual"),
    LINGUAGEM_RECEPTIVA(3, "Linguagem Receptiva"),
    IMITACAO_MOTORA(4, "Imitação Motora"),
    IMITACAO_VOCAL(5, "Imitação Vocal"),
    SOLICITACOES(6, "Solicitações"),
    NOMEACAO(7, "Nomeação"),
    INTRAVERBAL(8, "Intraverbal"),
    VOCALIZACOES_SPONTANEAS(9, "Vocalizações Espontâneas"),
    GRAMATICA_E_SINTAXE(10, "Gramática e Sintaxe"),
    JOGOS_E_LAZER(11, "Jogos e Lazer"),
    INTERACAO_SOCIAL(12, "Interação Social"),
    INSTRUCOES_EM_GRUPO(13, "Instruções em Grupo"),
    RESPOSTAS_GENERALIZADAS(14, "Respostas Generalizadas"),
    LEITURA(15, "Leitura"),
    MATEMATICA(16, "Matemática"),
    ESCRITA(17, "Escrita"),
    ORTOGRAFIA(18, "Ortografia"),
    VESTIMENTA(19, "Vestimenta"),
    ALIMENTACAO(20, "Alimentação"),
    PREPARACAO(21, "Preparação"),
    USO_DO_BANHEIRO(22, "Uso do Banheiro"),
    HABILIDADES_MOTORAS_GROSSAS(23, "Habilidades Motoras Grossas"),
    HABILIDADES_MOTORAS_FINAS(24, "Habilidades Motoras Finas");

    private final int id;
    private final String name;
    private static final Map<Integer, AbllsHabilidadeEnum> lookup = new HashMap<>();

    static {
        for (AbllsHabilidadeEnum s : AbllsHabilidadeEnum.values()) {
            lookup.put(s.getId(), s);
        }
    }

    AbllsHabilidadeEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AbllsHabilidadeEnum getByCod(Integer cod) {
        return lookup.get(cod);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
