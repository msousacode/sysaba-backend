package br.com.sysaba.modules.avaliacoes.ablls.dto;

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
    VOCALIZACOES_ESPONTANEAS(9, "Vocalizações Espontâneas"),
    GRAMATICA_E_SINTAXE(10, "Gramática e Sintaxe"),
    JOGOS_E_LAZER(11, "Jogos e Lazer"),
    INTERACAO_SOCIAL(12, "Interação Social"),
    INSTRUCOES_EM_GRUPO(13, "Instruções em Grupo"),
    ROTINAS_CLASSE(14, "Seguir Rotinas de Classe"),
    RESPOSTAS_GENERALIZADAS(15, "Respostas Generalizadas"),
    LEITURA(16, "Leitura"),
    MATEMATICA(17, "Matemática"),
    ESCRITA(18, "Escrita"),
    ORTOGRAFIA(19, "Ortografia"),
    VESTIMENTA(20, "Vestimenta"),
    ALIMENTACAO(21, "Alimentação"),
    PREPARACAO(22, "Preparação"),
    USO_DO_BANHEIRO(23, "Uso do Banheiro"),
    HABILIDADES_MOTORAS_GROSSAS(24, "Habilidades Motoras Grossas"),
    HABILIDADES_MOTORAS_FINAS(25, "Habilidades Motoras Finas");

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
