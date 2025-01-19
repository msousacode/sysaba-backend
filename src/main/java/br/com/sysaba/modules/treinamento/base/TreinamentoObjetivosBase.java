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

    @Column(name = "descricao_alvo", length = 500)
    private String descricaoAlvo;

    @Column(name = "nome_alvo", nullable = false)
    private String nomeAlvo;

    @Column(name = "pergunta", length = 500)
    private String pergunta;

    @JoinColumn(name = "treinamento_base_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TreinamentoBase treinamentoBase;

    public TreinamentoObjetivosBase() {
    }

    public UUID getTreinamentoObjetivoId() {
        return treinamentoObjetivoId;
    }

    public void setTreinamentoObjetivoId(UUID treinamentoObjetivoId) {
        this.treinamentoObjetivoId = treinamentoObjetivoId;
    }

    public String getDescricaoAlvo() {
        return descricaoAlvo;
    }

    public void setDescricaoAlvo(String descricaoAlvo) {
        this.descricaoAlvo = descricaoAlvo;
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

    public TreinamentoBase getTreinamentoBase() {
        return treinamentoBase;
    }

    public void setTreinamentoBase(TreinamentoBase treinamentoBase) {
        this.treinamentoBase = treinamentoBase;
    }
}
