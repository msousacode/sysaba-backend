package br.com.sysaba.modules.acesso.dto;

import java.util.UUID;

public class UsuarioInfoDTO {

    private UUID usuarioId;
    private String fullName;
    private String email;
    private Boolean termoAceite;

    public UsuarioInfoDTO() {
    }

    public UsuarioInfoDTO(UUID usuarioId, String nome, String email, Boolean termoAceite) {
        this.usuarioId = usuarioId;
        this.fullName = nome;
        this.email = email;
        this.termoAceite = termoAceite;
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

    public Boolean getTermoAceite() {
        return termoAceite;
    }

    public void setTermoAceite(Boolean termoAceite) {
        this.termoAceite = termoAceite;
    }
}
