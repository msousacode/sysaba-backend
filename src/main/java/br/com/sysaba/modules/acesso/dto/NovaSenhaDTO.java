package br.com.sysaba.modules.acesso.dto;

import java.util.UUID;

public class NovaSenhaDTO {

    private String senha;
    private UUID userId;
    private UUID keyId;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getKeyId() {
        return keyId;
    }

    public void setKeyId(UUID keyId) {
        this.keyId = keyId;
    }
}
