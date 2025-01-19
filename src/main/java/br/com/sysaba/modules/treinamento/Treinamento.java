package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.anotacao.Anotacao;
import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.coleta.Coleta;
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

    @Column(name = "treinamento", nullable = false, length = 500)
    private String treinamento;

    @Column(name = "descricao", nullable = false, length = 500)
    private String descricao;

    @OneToMany(mappedBy = "treinamento", cascade = CascadeType.PERSIST)
    private List<Alvo> alvos;

    @OneToMany(mappedBy = "treinamento")
    private List<TreinamentoAtendimento> treinamentoAtendimentos;

    @OneToMany(mappedBy = "treinamento")
    private List<Coleta> coletas;

    @OneToMany(mappedBy = "treinamento")
    private List<Anotacao> anotacoes;

    public Treinamento() {
        super(LocalDateTime.now());
    }

    public Treinamento(LocalDateTime createdAt, UUID treinamentoId, String protocolo, String treinamento, String descricao, List<Alvo> alvos, List<Coleta> coletas) {
        super(createdAt);
        this.treinamentoId = treinamentoId;
        this.protocolo = protocolo;
        this.treinamento = treinamento;
        this.descricao = descricao;
        this.alvos = alvos;
        this.coletas = coletas;
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

    public List<Coleta> getColetas() {
        return coletas;
    }

    public void setColetas(List<Coleta> coletas) {
        this.coletas = coletas;
    }

    public List<Anotacao> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<Anotacao> anotacoes) {
        this.anotacoes = anotacoes;
    }

    public List<TreinamentoAtendimento> getTreinamentoAtendimentos() {
        return treinamentoAtendimentos;
    }

    public void setTreinamentoAtendimentos(List<TreinamentoAtendimento> treinamentoAtendimentos) {
        this.treinamentoAtendimentos = treinamentoAtendimentos;
    }
}
