package br.com.sysaba.modules.treinamento.base;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "treinamentos_objetivos_base")
public class TreinamentoObjetivosBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treinamento_objetivo_id")
    private UUID treinamentoObjetivoId;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;

    @JoinColumn(name = "treinamento_base_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TreinamentoBase treinamentoBase;

    public TreinamentoObjetivosBase() {
    }

    public TreinamentoObjetivosBase(UUID treinamentoObjetivoId, String titulo, String descricao, TreinamentoBase treinamentoBase) {
        this.treinamentoObjetivoId = treinamentoObjetivoId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.treinamentoBase = treinamentoBase;
    }

    public UUID getTreinamentoObjetivoId() {
        return treinamentoObjetivoId;
    }

    public void setTreinamentoObjetivoId(UUID treinamentoObjetivoId) {
        this.treinamentoObjetivoId = treinamentoObjetivoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TreinamentoBase getTreinamentoBase() {
        return treinamentoBase;
    }

    public void setTreinamentoBase(TreinamentoBase treinamentoBase) {
        this.treinamentoBase = treinamentoBase;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
