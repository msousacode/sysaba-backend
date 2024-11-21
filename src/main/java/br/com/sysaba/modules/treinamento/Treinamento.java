package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.dto.TreinamentoItemDTO;
import br.com.sysaba.modules.alvo.Alvo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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

    @Column(name = "treinamento", nullable = false, length = 500)
    private String treinamento;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @OneToMany(mappedBy = "treinamento")
    private List<Alvo> alvos;

    @OneToMany(mappedBy = "treinamento")
    private List<TreinamentoAtendimento> treinamentoAtendimentos;

    public Treinamento() {
        super(LocalDateTime.now());
    }

    public Treinamento(LocalDateTime createdAt, UUID treinamentoId, String protocolo, String treinamento, String descricao, List<Alvo> alvos) {
        super(createdAt);
        this.treinamentoId = treinamentoId;
        this.protocolo = protocolo;
        this.treinamento = treinamento;
        this.descricao = descricao;
        this.alvos = alvos;
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

    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = treinamento;
    }
}
