package br.com.sysaba.usuario;

import br.com.sysaba.commons.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "usuario_id")
    private UUID usuarioId;

    @Column(name = "documento")
    private String documento;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "demontracao_restore")
    private String demonstracaoRestore;

    @Column(name = "primeiro_acesso_realizado")
    private Boolean primeiroAcessoRealizado;

    public Usuario(LocalDateTime createdAt, UUID usuarioId, String documento, String email, String fullName, String avatarUrl, String demonstracaoRestore, Boolean primeiroAcessoRealizado) {
        super(createdAt);
        this.usuarioId = usuarioId;
        this.documento = documento;
        this.email = email;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.demonstracaoRestore = demonstracaoRestore;
        this.primeiroAcessoRealizado = primeiroAcessoRealizado;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDemonstracaoRestore() {
        return demonstracaoRestore;
    }

    public void setDemonstracaoRestore(String demonstracaoRestore) {
        this.demonstracaoRestore = demonstracaoRestore;
    }

    public Boolean getPrimeiroAcessoRealizado() {
        return primeiroAcessoRealizado;
    }

    public void setPrimeiroAcessoRealizado(Boolean primeiroAcessoRealizado) {
        this.primeiroAcessoRealizado = primeiroAcessoRealizado;
    }
}
