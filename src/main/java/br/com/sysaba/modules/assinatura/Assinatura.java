package br.com.sysaba.modules.assinatura;

import br.com.sysaba.core.commons.BaseEntity;
import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assinaturas")
public class Assinatura extends BaseEntity {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Assinatura(LocalDateTime createdAt, UUID assinaturaId, String motivoCancelamento, TipoAssinaturaEnum tipoAssinatura, LocalDateTime dataCancelamento, LocalDateTime dataContratacao, LocalDateTime dataInicioAssinatura, Usuario usuario) {
        super(createdAt);
        this.assinaturaId = assinaturaId;
        this.motivoCancelamento = motivoCancelamento;
        this.tipoAssinatura = tipoAssinatura;
        this.dataCancelamento = dataCancelamento;
        this.dataContratacao = dataContratacao;
        this.dataInicioAssinatura = dataInicioAssinatura;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
