package br.com.sysaba.modules.acesso.dto;

import java.util.UUID;

public class UsuarioInfoDTO {

    private UUID usuarioId;
    private String fullName;
    private String email;

    public UsuarioInfoDTO() {
    }

    public UsuarioInfoDTO(UUID usuarioId, String nome, String email) {
        this.usuarioId = usuarioId;
        this.fullName = nome;
        this.email = email;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
