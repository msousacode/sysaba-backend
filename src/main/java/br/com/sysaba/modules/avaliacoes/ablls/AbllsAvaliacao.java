package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsDTO;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsHabilidadeEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ablls_avaliacoes")
public class AbllsAvaliacao extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "ablls_id")
    private UUID abllsId;

    @Column(name = "objetivo_documento")
    private String objetivoDocumento;

    @Column(name = "habilidate_coleta", nullable = false)
    private String habilidade;

    @Column(name = "protocolo")
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public AbllsAvaliacao() {
        super(LocalDateTime.now());
    }

    public static AbllsAvaliacao from(AbllsDTO abllsDTO, Aprendiz aprendiz) {
        AbllsAvaliacao avaliacao = new AbllsAvaliacao();
        avaliacao.setAtivo(true);
        avaliacao.setAprendiz(aprendiz);
        avaliacao.setProtocolo(abllsDTO.getProtocolo());
        avaliacao.setObjetivoDocumento(abllsDTO.getObjetivo());
        avaliacao.setHabilidade(abllsDTO.getHabilidades());
        return avaliacao;
    }

    public UUID getAbllsId() {
        return abllsId;
    }

    public void setAbllsId(UUID abllsId) {
        this.abllsId = abllsId;
    }

    public String getObjetivoDocumento() {
        return objetivoDocumento;
    }

    public void setObjetivoDocumento(String objetivoDocumento) {
        this.objetivoDocumento = objetivoDocumento;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }
}
