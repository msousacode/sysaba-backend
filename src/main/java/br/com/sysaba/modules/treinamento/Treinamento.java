package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.models.Tenantable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "treinamentos")
public class Treinamento extends Tenantable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treinamento_id")
    private UUID treinamentoId;

    @Column(name = "treinamento", nullable = false)
    private String treinamento;

    @Column(name = "protocolo", nullable = false)
    private String protocolo;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    public Treinamento() {
        super(LocalDateTime.now());
    }

    public Treinamento(LocalDateTime createdAt, UUID treinamentoId, String treinamento, String protocolo, String descricao) {
        super(createdAt);
        this.treinamentoId = treinamentoId;
        this.treinamento = treinamento;
        this.protocolo = protocolo;
        this.descricao = descricao;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = treinamento;
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
}
