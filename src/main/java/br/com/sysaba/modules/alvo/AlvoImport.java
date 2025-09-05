package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.anotacao.Anotacao;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "alvos_import")
public class AlvoImport extends Tenantable {

    public AlvoImport() {
        super(LocalDateTime.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alvo_import_id")
    private UUID alvoId;

    @Column(name = "nome_alvo", nullable = false)
    private String nomeAlvo;

    @Column(name = "tag")
    private String tag;

    @Column(name = "concluido")
    private boolean concluido;

    @Column(name = "encerrado")
    private boolean encerrado;

    @Column(name = "total_estrela_positiva")
    private Integer totalEstrelaPositiva;

    @Column(name = "total_estrela_negativa")
    private Integer totalEstrelaNegativa;

    @Column(name = "alvo_pai_id", nullable = false)
    private UUID alvoPai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = true)
    private Aprendiz aprendiz;

    @OneToMany(mappedBy = "alvoImport")
    private List<Anotacao> anotacao;

    public List<Anotacao> getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(List<Anotacao> anotacao) {
        this.anotacao = anotacao;
    }

    public static AlvoImport convert(String nomeAlvo, String tag, Aprendiz aprendiz) {
        AlvoImport alvo = new AlvoImport();
        alvo.setNomeAlvo(nomeAlvo);
        alvo.setTag(tag);
        alvo.setAprendiz(aprendiz);
        alvo.setConcluido(false);
        alvo.setEncerrado(false);
        return alvo;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public Integer getTotalEstrelaPositiva() {
        return totalEstrelaPositiva;
    }

    public void setTotalEstrelaPositiva(Integer totalEstrelaPositiva) {
        this.totalEstrelaPositiva = totalEstrelaPositiva;
    }

    public Integer getTotalEstrelaNegativa() {
        return totalEstrelaNegativa;
    }

    public void setTotalEstrelaNegativa(Integer totalEstrelaNegativa) {
        this.totalEstrelaNegativa = totalEstrelaNegativa;
    }

    public UUID getAlvoPai() {
        return alvoPai;
    }

    public void setAlvoPai(UUID alvoPai) {
        this.alvoPai = alvoPai;
    }    
}
