package br.com.sysaba.modules.atendimento.dto;

public class TreinamentoItemDTO {
    private String treinamento;
    private String protocolo;
    private boolean ativo;
    private ConfiguracoesDTO configuracoes;

    public TreinamentoItemDTO() {}

    public TreinamentoItemDTO(String treinamento, String protocolo, boolean ativo, ConfiguracoesDTO configuracoesDTO) {
        this.treinamento = treinamento;
        this.protocolo = protocolo;
        this.ativo = ativo;
        this.configuracoes = configuracoesDTO;
    }

    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = treinamento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ConfiguracoesDTO getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ConfiguracoesDTO configuracoes) {
        this.configuracoes = configuracoes;
    }
}