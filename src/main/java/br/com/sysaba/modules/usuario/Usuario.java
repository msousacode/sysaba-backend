package br.com.sysaba.modules.usuario;

import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.assinatura.Assinatura;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {

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

    @Column(name = "primeiro_acesso_realizado")
    private Boolean primeiroAcessoRealizado;

    @Column(name = "senha")
    private String senha;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "criado_por")
    private UUID criadoPor;

    @Column(name = "tentant_id")
    private UUID tenantId;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Assinatura assinatura;

    public Usuario(UUID usuarioId, String documento, String email, String fullName, String avatarUrl, Boolean primeiroAcessoRealizado, String senha, LocalDateTime createdAt, Boolean ativo, UUID criadoPor, UUID tenantId) {
        this.usuarioId = usuarioId;
        this.documento = documento;
        this.email = email;
        this.fullName = fullName;
        this.avatarUrl = avatarUrl;
        this.primeiroAcessoRealizado = primeiroAcessoRealizado;
        this.senha = senha;
        this.createdAt = createdAt;
        this.ativo = ativo;
        this.criadoPor = criadoPor;
        this.tenantId = tenantId;
    }

    public Usuario() {

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

    public Boolean getPrimeiroAcessoRealizado() {
        return primeiroAcessoRealizado;
    }

    public void setPrimeiroAcessoRealizado(Boolean primeiroAcessoRealizado) {
        this.primeiroAcessoRealizado = primeiroAcessoRealizado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(UUID criadoPor) {
        this.criadoPor = criadoPor;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.ativo = Boolean.TRUE;
        this.primeiroAcessoRealizado = Boolean.FALSE;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }
}
