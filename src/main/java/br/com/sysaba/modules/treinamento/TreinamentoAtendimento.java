package br.com.sysaba.modules.treinamento;

import br.com.sysaba.modules.atendimento.Atendimento;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "treinamentos_atendimentos")
public class TreinamentoAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treinamentos_atendimentos_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "treinamento_id")
    private Treinamento treinamento;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private Atendimento atendimento;

    @OneToOne(mappedBy = "treinamentoAtendimento", cascade = CascadeType.PERSIST)
    private Configuracoes configuracoes;

    public TreinamentoAtendimento() {
    }

    public TreinamentoAtendimento(Treinamento treinamento, Atendimento atendimento) {
        this.treinamento = treinamento;
        this.atendimento = atendimento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public Configuracoes getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(Configuracoes configuracoes) {
        this.configuracoes = configuracoes;
    }
}
