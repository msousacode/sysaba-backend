package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "atendimentos")
public class Atendimento extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "atendimento_id")
    private UUID atendimentoId;

    @Column(name = "data_inicio", nullable = false)
    private LocalDateTime dataInicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public Atendimento(){
        super(LocalDateTime.now());
    }

    public Atendimento(LocalDateTime createdAt, UUID atendimentoId, LocalDateTime dataInicio, Aprendiz aprendiz) {
        super(createdAt);
        this.atendimentoId = atendimentoId;
        this.dataInicio = dataInicio;
        this.aprendiz = aprendiz;
    }

    public UUID getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(UUID atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }
}
