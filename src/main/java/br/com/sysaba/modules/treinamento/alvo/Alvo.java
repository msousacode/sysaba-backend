package br.com.sysaba.modules.treinamento.alvo;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.treinamento.Treinamento;
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

    @Column(name = "nome_alvo")
    private String nomeAlvo;

    @Column(name = "pergunta")
    private String pergunta;

    @Column(name = "descricao_alvo")
    private String descricaoAlvo;

    @Column(name = "treinamento_uuid_fk")
    private String treinamentoUuidFk;

    @Column(name = "tipo_aprendizado")
    private String tipoAprendizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    // Construtor padrão
    public Alvo() {
        super(LocalDateTime.now());
    }

    public Alvo(LocalDateTime createdAt, UUID alvoId, String nomeAlvo, String pergunta, String descricaoAlvo, String treinamentoUuidFk, String tipoAprendizado) {
        super(createdAt);
        this.alvoId = alvoId;
        this.nomeAlvo = nomeAlvo;
        this.pergunta = pergunta;
        this.descricaoAlvo = descricaoAlvo;
        this.treinamentoUuidFk = treinamentoUuidFk;
        this.tipoAprendizado = tipoAprendizado;
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

    public String getTipoAprendizado() {
        return tipoAprendizado;
    }

    public void setTipoAprendizado(String tipoAprendizado) {
        this.tipoAprendizado = tipoAprendizado;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
