package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.commons.BaseEntity;
import br.com.sysaba.modules.treinamento.Alvo;
import br.com.sysaba.modules.treinamento.Treinamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "anotacoes")
public class Anotacao extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "alvo_id")
    private UUID alvoId;

    @Column(name = "data_anotacao", nullable = false)
    private LocalDateTime dataAnotacao;

    @Column(name = "anotacao", nullable = false, length = 500)
    private String anotacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alvo_id", nullable = false)
    private Alvo alvo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    public Anotacao(LocalDateTime createdAt, UUID alvoId, LocalDateTime dataAnotacao, String anotacao, Alvo alvo, Treinamento treinamento) {
        super(createdAt);
        this.alvoId = alvoId;
        this.dataAnotacao = dataAnotacao;
        this.anotacao = anotacao;
        this.alvo = alvo;
        this.treinamento = treinamento;
    }

    public UUID getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(UUID alvoId) {
        this.alvoId = alvoId;
    }

    public LocalDateTime getDataAnotacao() {
        return dataAnotacao;
    }

    public void setDataAnotacao(LocalDateTime dataAnotacao) {
        this.dataAnotacao = dataAnotacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Alvo getAlvo() {
        return alvo;
    }

    public void setAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
