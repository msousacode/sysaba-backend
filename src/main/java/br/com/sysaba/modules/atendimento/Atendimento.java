package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.anotacao.Anotacao;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.treinamento.TreinamentoAtendimento;
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
    private List<TreinamentoAtendimento> treinamentoAtendimentos;

    @OneToMany(mappedBy = "atendimento")
    private List<Anotacao> anotacoes;

    public Atendimento() {
        super(LocalDateTime.now());
    }

    public Atendimento(LocalDateTime createdAt, UUID atendimentoId, LocalDate dataInicio, Aprendiz aprendiz, List<Anotacao> anotacoes) {
        super(createdAt);
        this.atendimentoId = atendimentoId;
        this.dataInicio = dataInicio;
        this.aprendiz = aprendiz;
        this.anotacoes = anotacoes;
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


    public List<TreinamentoAtendimento> getTreinamentoAtendimentos() {
        return treinamentoAtendimentos;
    }

    public void setTreinamentoAtendimentos(List<TreinamentoAtendimento> treinamentoAtendimentos) {
        this.treinamentoAtendimentos = treinamentoAtendimentos;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }
}
