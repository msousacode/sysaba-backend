package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.treinamento.Treinamento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "anotacoes")
public class Anotacao extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "anotacao_id")
    private UUID anotacaoId;

    @Column(name = "data_anotacao", nullable = false)
    private LocalDate dataAnotacao;

    @Column(name = "anotacao", nullable = false, length = 500)
    private String anotacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coleta_id", nullable = false)
    private Coleta coleta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    public Anotacao() {
        super(LocalDateTime.now());
    }

    public Anotacao(LocalDateTime createdAt, UUID anotacaoId, LocalDate dataAnotacao, String anotacao, Coleta coleta, Treinamento treinamento) {
        super(createdAt);
        this.anotacaoId = anotacaoId;
        this.dataAnotacao = dataAnotacao;
        this.anotacao = anotacao;
        this.coleta = coleta;
        this.treinamento = treinamento;
    }

    public UUID getAnotacaoId() {
        return anotacaoId;
    }

    public void setAnotacaoId(UUID anotacaoId) {
        this.anotacaoId = anotacaoId;
    }

    public LocalDate getDataAnotacao() {
        return dataAnotacao;
    }

    public void setDataAnotacao(LocalDate dataAnotacao) {
        this.dataAnotacao = dataAnotacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public Coleta getColeta() {
        return coleta;
    }

    public void setColeta(Coleta coleta) {
        this.coleta = coleta;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
