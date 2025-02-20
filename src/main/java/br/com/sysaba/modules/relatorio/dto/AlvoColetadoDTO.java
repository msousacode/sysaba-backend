package br.com.sysaba.modules.relatorio.dto;

import java.util.List;

public class AlvoColetadoDTO {
    private String dataColeta;
    private String nomeAlvo;
    private String pergunta;
    private String descricaoAlvo;
    private String resposta;
    private String coletadoPor;
    private List<AnotacaoDTO> anotacoes;

    public AlvoColetadoDTO() {
    }

    public AlvoColetadoDTO(String dataColeta, String nomeAlvo, String pergunta, String descricaoAlvo, String resposta, List<AnotacaoDTO> anotacoes) {
        this.dataColeta = dataColeta;
        this.nomeAlvo = nomeAlvo;
        this.pergunta = pergunta;
        this.descricaoAlvo = descricaoAlvo;
        this.resposta = resposta;
        this.anotacoes = anotacoes;
    }

    public String getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(String dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDescricaoAlvo() {
        return descricaoAlvo;
    }

    public void setDescricaoAlvo(String descricaoAlvo) {
        this.descricaoAlvo = descricaoAlvo;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public List<AnotacaoDTO> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<AnotacaoDTO> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public String getColetadoPor() {
        return coletadoPor;
    }

    public void setColetadoPor(String coletadoPor) {
        this.coletadoPor = coletadoPor;
    }
}
