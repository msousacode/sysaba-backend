package br.com.sysaba.modules.treinamento.base;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "treinamentos_base")
public class TreinamentoBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "treinamento_base_id")
    private UUID treinamentoBaseId;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", length = 500, nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "protocolo", nullable = false)
    private ProtocoloEnum protocolo;

    @Enumerated(EnumType.STRING)
    @Column(name = "habilidade", nullable = false)
    private HabilidadeBaseEnum habilidade;

    @Column(name = "importId")
    private UUID importId;

    @OneToMany(mappedBy = "treinamentoBase")
    private List<TreinamentoObjetivosBase> treinos;

    public TreinamentoBase() {
    }

    public TreinamentoBase(UUID treinamentoBaseId, String titulo, String descricao, ProtocoloEnum protocolo, HabilidadeBaseEnum habilidade) {
        this.treinamentoBaseId = treinamentoBaseId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.protocolo = protocolo;
        this.habilidade = habilidade;
    }

    public ProtocoloEnum getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(ProtocoloEnum protocolo) {
        this.protocolo = protocolo;
    }

    public HabilidadeBaseEnum getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(HabilidadeBaseEnum habilidade) {
        this.habilidade = habilidade;
    }

    public UUID getTreinamentoBaseId() {
        return treinamentoBaseId;
    }

    public void setTreinamentoBaseId(UUID treinamentoBaseId) {
        this.treinamentoBaseId = treinamentoBaseId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<TreinamentoObjetivosBase> getTreinos() {
        return treinos;
    }

    public void setTreinos(List<TreinamentoObjetivosBase> treinos) {
        this.treinos = treinos;
    }

    public UUID getImportId() {
        return importId;
    }

    public void setImportId(UUID importId) {
        this.importId = importId;
    }
}
