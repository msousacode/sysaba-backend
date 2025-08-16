package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.anotacao.Anotacao;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "aprendizes")
public class Aprendiz extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "aprendiz_id")
    private UUID aprendizId;

    @Column(name = "nome_aprendiz", nullable = false)
    private String nomeAprendiz;

    @Column(name = "data_anotacao", nullable = false)
    private LocalDate nascAprendiz;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "nome_pai")
    private String nomePai;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "observacao")
    private String observacao;

    @OneToMany(mappedBy = "aprendiz")
    private List<Anotacao> anotacao;

    public Aprendiz(LocalDateTime createdAt, UUID aprendizId, String nomeAprendiz, LocalDate nascAprendiz, String nomeMae, String nomePai, String nomeResponsavel, String observacao) {
        super(createdAt);
        this.aprendizId = aprendizId;
        this.nomeAprendiz = nomeAprendiz;
        this.nascAprendiz = nascAprendiz;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.nomeResponsavel = nomeResponsavel;
        this.observacao = observacao;
    }

    public Aprendiz() {
        super(LocalDateTime.now());
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public LocalDate getNascAprendiz() {
        return nascAprendiz;
    }

    public void setNascAprendiz(LocalDate nascAprendiz) {
        this.nascAprendiz = nascAprendiz;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<Anotacao> getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(List<Anotacao> anotacao) {
        this.anotacao = anotacao;
    }
}
