package br.com.sysaba.modules.acesso.dto;

import br.com.sysaba.modules.acesso.PerfilEnum;

import java.util.UUID;

public class UsuarioInfoDTO {

    private UUID usuarioId;
    private String fullName;
    private String email;
    private Boolean termoAceite;
    private String documento;
    private AssinaturaDTO assinatura;
    private PerfilEnum perfil;
    private Boolean ativo;

    public UsuarioInfoDTO() {
    }

    public UsuarioInfoDTO(UUID usuarioId, String nome, String email, Boolean termoAceite, String documento) {
        this.usuarioId = usuarioId;
        this.fullName = nome;
        this.email = email;
        this.termoAceite = termoAceite;
        this.documento = documento;
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

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public AssinaturaDTO getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(AssinaturaDTO assinatura) {
        this.assinatura = assinatura;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
