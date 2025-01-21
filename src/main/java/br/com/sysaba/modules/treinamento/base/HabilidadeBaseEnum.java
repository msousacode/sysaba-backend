package br.com.sysaba.modules.treinamento.base;

public enum HabilidadeBaseEnum {
    ATENCAO("Atenção"),
    IMITACAO("Imitação"),
    LINGUAGEM_RECEPTIVA("Linguagem Receptiva"),
    LINGUAGEM_EXPRESSIVA("Linguagem Expressiva"),
    PRE_ACADEMICAS("Pré-acadêmicas"),
    MEMORIA("Memória"),
    COORDENACAO("Coordenação"),
    RACIOCINIO("Raciocínio"),
    SOCIALIZACAO("Socialização"),
    AUTOAJUDA("Autoajuda");

    private String descricao;

    HabilidadeBaseEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
