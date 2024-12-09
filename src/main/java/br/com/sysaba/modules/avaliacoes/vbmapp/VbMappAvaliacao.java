package br.com.sysaba.modules.avaliacoes.vbmapp;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vbmapp_avaliacoes")
public class VbMappAvaliacao extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "vbmapp_id")
    private UUID vbMappId;

    @Column(name = "objetivo_documento")
    private String objetivoDocumento;

    @Column(name = "niveis_coleta", nullable = false)
    private String niveisColeta;

    @Column(name = "protocolo")
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public static VbMappAvaliacao from(VbMappDTO dto, Aprendiz aprendiz) {
        VbMappAvaliacao vbMapp = new VbMappAvaliacao();
        vbMapp.setAprendiz(aprendiz);
        vbMapp.setNiveisColeta(dto.getNiveisColeta());
        vbMapp.setObjetivoDocumento(dto.getObjetivoDocumento());
        vbMapp.setProtocolo(dto.getProtocolo());
        return vbMapp;
    }

    public VbMappAvaliacao() {
        super(LocalDateTime.now());
        this.setAtivo(true);
    }

    public VbMappAvaliacao(LocalDateTime createdAt, UUID vbMappId, String objetivoDocumento, String niveisColeta, Aprendiz aprendiz) {
        super(createdAt);
        this.vbMappId = vbMappId;
        this.objetivoDocumento = objetivoDocumento;
        this.niveisColeta = niveisColeta;
        this.aprendiz = aprendiz;
    }

    public UUID getVbMappId() {
        return vbMappId;
    }

    public void setVbMappId(UUID vbMappId) {
        this.vbMappId = vbMappId;
    }

    public String getObjetivoDocumento() {
        return objetivoDocumento;
    }

    public void setObjetivoDocumento(String objetivoDocumento) {
        this.objetivoDocumento = objetivoDocumento;
    }

    public String getNiveisColeta() {
        return niveisColeta;
    }

    public void setNiveisColeta(String niveisColeta) {
        this.niveisColeta = niveisColeta;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
}
