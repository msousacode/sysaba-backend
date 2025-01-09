package br.com.sysaba.modules.avaliacoes.vbmapp.dto;

import br.com.sysaba.modules.aprendiz.Aprendiz;

import java.util.UUID;

public class VbMappBarreiraDTO {

    private UUID vbMappBarreiraId;

    private String questao;

    private String resposta;

    private String descricao;

    private Integer codigo;

    public VbMappBarreiraDTO(){}

    public UUID getVbMappBarreiraId() {
        return vbMappBarreiraId;
    }

    public void setVbMappBarreiraId(UUID vbMappBarreiraId) {
        this.vbMappBarreiraId = vbMappBarreiraId;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
