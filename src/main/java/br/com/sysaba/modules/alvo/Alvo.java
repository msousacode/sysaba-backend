package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.base.TreinamentoObjetivosBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alvos")
public class Alvo extends Tenantable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alvo_id")
    private UUID alvoId;

    @Column(name = "nome_alvo", nullable = false)
    private String nomeAlvo;

    @Column(name = "pergunta", nullable = true)
    private String pergunta;

    @Column(name = "descricao_alvo", nullable = true)
    private String descricaoAlvo;

    @Column(name = "treinamento_uuid_fk", nullable = true)
    private String treinamentoUuidFk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = true)
    private Treinamento treinamento;


    public static Alvo convert(TreinamentoObjetivosBase treinamentoObjetivosBase, Treinamento treinamento) {
        Alvo alvo = new Alvo();
        alvo.setDescricaoAlvo(treinamentoObjetivosBase.getDescricaoAlvo());
        alvo.setPergunta(treinamentoObjetivosBase.getPergunta());
        alvo.setNomeAlvo(treinamentoObjetivosBase.getNomeAlvo());
        alvo.setTreinamento(treinamento);
        return alvo;
    }

    // Construtor padrão
    public Alvo() {
        super(LocalDateTime.now());
    }

    public Alvo(LocalDateTime createdAt, UUID alvoId, String nomeAlvo, String pergunta, String descricaoAlvo, String treinamentoUuidFk) {
        super(createdAt);
        this.alvoId = alvoId;
        this.nomeAlvo = nomeAlvo;
        this.pergunta = pergunta;
        this.descricaoAlvo = descricaoAlvo;
        this.treinamentoUuidFk = treinamentoUuidFk;
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

    public String getTreinamentoUuidFk() {
        return treinamentoUuidFk;
    }

    public void setTreinamentoUuidFk(String treinamentoUuidFk) {
        this.treinamentoUuidFk = treinamentoUuidFk;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
