package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.treinamento.Treinamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "atendimentos")
public class Atendimento extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "atendimento_id")
    private UUID atendimentoId;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @OneToMany(mappedBy = "atendimento")
    private List<Treinamento> treinamentos;

    public Atendimento() {
        super(LocalDateTime.now());
    }

    public Atendimento(LocalDateTime createdAt, UUID atendimentoId, LocalDate dataInicio, Aprendiz aprendiz, List<Treinamento> treinamento) {
        super(createdAt);
        this.atendimentoId = atendimentoId;
        this.dataInicio = dataInicio;
        this.aprendiz = aprendiz;
        this.treinamentos = treinamento;
    }

    public UUID getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(UUID atendimentoId) {
        this.atendimentoId = atendimentoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public List<Treinamento> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<Treinamento> treinamentos) {
        this.treinamentos = treinamentos;
    }
}
