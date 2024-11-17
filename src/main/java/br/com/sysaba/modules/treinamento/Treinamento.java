package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.treinamento.alvo.Alvo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treinamentos")
public class Treinamento extends Tenantable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treinamento_id")
    private UUID treinamentoId;

    @Column(name = "protocolo", nullable = false)
    private String protocolo;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @OneToMany(mappedBy = "treinamento")
    private List<Alvo> alvos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atendimento_id")
    private Atendimento atendimento;

    public Treinamento() {
        super(LocalDateTime.now());
    }

    public Treinamento(LocalDateTime createdAt, UUID treinamentoId, String protocolo, String descricao, List<Alvo> alvos, Atendimento atendimento) {
        super(createdAt);
        this.treinamentoId = treinamentoId;
        this.protocolo = protocolo;
        this.descricao = descricao;
        this.alvos = alvos;
        this.atendimento = atendimento;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Alvo> getAlvos() {
        return alvos;
    }

    public void setAlvos(List<Alvo> alvos) {
        this.alvos = alvos;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }
}
