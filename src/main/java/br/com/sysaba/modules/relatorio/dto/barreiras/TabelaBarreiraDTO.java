package br.com.sysaba.modules.relatorio.dto.barreiras;

import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappBarreira;

public class TabelaBarreiraDTO {

    private Integer codigo;

    private String pergunta;

    private String descricao;

    private Integer resposta;

    public TabelaBarreiraDTO(Integer codigo, String pergunta, String descricao, Integer resposta) {
        this.codigo = codigo;
        this.pergunta = pergunta;
        this.descricao = descricao;
        this.resposta = resposta;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }

    public static TabelaBarreiraDTO convert(VbMappBarreira vbMappBarreira) {
        return new TabelaBarreiraDTO(vbMappBarreira.getCodigo(), vbMappBarreira.getQuestao(), vbMappBarreira.getDescricao(), Integer.valueOf(vbMappBarreira.getResposta()));
    }
}
