package br.com.sysaba.modules.coleta.dto;

import java.util.UUID;

public class RespostaDTO {
    private UUID uuid;
    private String resposta;

    public RespostaDTO() {
    }

    public RespostaDTO(UUID uui, String resposta) {
        this.uuid = uui;
        this.resposta = resposta;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
