package br.com.sysaba.modules.assinatura;

import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assinaturas")
public class Assinatura {

    @Id
    @GeneratedValue
    @Column(name = "assinatura_id")
    private UUID assinaturaId;

    @Column(name = "motivo_cancelamento")
    private String motivoCancelamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_assinatura", nullable = false)
    private TipoAssinaturaEnum tipoAssinatura;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Column(name = "data_contratacao")
    private LocalDateTime dataContratacao;

    @Column(name = "data_inicio_assinatura", nullable = false)
    private LocalDateTime dataInicioAssinatura;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "criado_por")
    private UUID criadoPor;

    @Column(name = "tentant_id", nullable = false)
    private UUID tenantId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "invoice_stripe_id")
    private String invoiceStripeId;

    public Assinatura(){}

    public Assinatura(String motivoCancelamento, TipoAssinaturaEnum tipoAssinatura, LocalDateTime dataCancelamento, LocalDateTime dataContratacao, LocalDateTime dataInicioAssinatura, LocalDateTime createdAt, Boolean ativo, UUID criadoPor, UUID tenantId, Usuario usuario) {
        this.motivoCancelamento = motivoCancelamento;
        this.tipoAssinatura = tipoAssinatura;
        this.dataCancelamento = dataCancelamento;
        this.dataContratacao = dataContratacao;
        this.dataInicioAssinatura = dataInicioAssinatura;
        this.createdAt = createdAt;
        this.ativo = ativo;
        this.criadoPor = criadoPor;
        this.tenantId = tenantId;
        this.usuario = usuario;
    }

    public UUID getAssinaturaId() {
        return assinaturaId;
    }

    public void setAssinaturaId(UUID assinaturaId) {
        this.assinaturaId = assinaturaId;
    }

    public String getMotivoCancelamento() {
        return motivoCancelamento;
    }

    public void setMotivoCancelamento(String motivoCancelamento) {
        this.motivoCancelamento = motivoCancelamento;
    }

    public TipoAssinaturaEnum getTipoAssinatura() {
        return tipoAssinatura;
    }

    public void setTipoAssinatura(TipoAssinaturaEnum tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public LocalDateTime getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDateTime dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public LocalDateTime getDataInicioAssinatura() {
        return dataInicioAssinatura;
    }

    public void setDataInicioAssinatura(LocalDateTime dataInicioAssinatura) {
        this.dataInicioAssinatura = dataInicioAssinatura;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.ativo = Boolean.TRUE;
    }

    public String getInvoiceStripeId() {
        return invoiceStripeId;
    }

    public void setInvoiceStripeId(String invoiceStripeId) {
        this.invoiceStripeId = invoiceStripeId;
    }

    public static Assinatura getInstance(Usuario usuario) {
        //@formatter:off
        return new Assinatura(
                null,
                TipoAssinaturaEnum.NAO_ASSINANTE,
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                Boolean.TRUE,
                usuario.getUsuarioId(),
                usuario.getUsuarioId(),
                usuario);
        //@formatter:off
    }
}
