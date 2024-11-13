package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.commons.BaseEntity;
import br.com.sysaba.modules.treinamento.Treinamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alvos")
public class Alvo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alvo_id")
    private UUID alvoId;

    @Column(name = "nome_alvo", nullable = false)
    private String nomeAlvo;

    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @Column(name = "descricao_alvo", nullable = false, length = 500)
    private String descricaoAlvo;

    @Column(name = "repetir", nullable = false)
    private Integer repetir;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    public Alvo(LocalDateTime createdAt, UUID alvoId, String nomeAlvo, String pergunta, String descricaoAlvo, Integer repetir, Treinamento treinamento) {
        super(createdAt);
        this.alvoId = alvoId;
        this.nomeAlvo = nomeAlvo;
        this.pergunta = pergunta;
        this.descricaoAlvo = descricaoAlvo;
        this.repetir = repetir;
        this.treinamento = treinamento;
    }

    public UUID getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(UUID alvoId) {
        this.alvoId = alvoId;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDescricaoAlvo() {
        return descricaoAlvo;
    }

    public void setDescricaoAlvo(String descricaoAlvo) {
        this.descricaoAlvo = descricaoAlvo;
    }

    public Integer getRepetir() {
        return repetir;
    }

    public void setRepetir(Integer repetir) {
        this.repetir = repetir;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
