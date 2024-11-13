package br.com.sysaba.modules.vbmapp;

import br.com.sysaba.core.commons.BaseEntity;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vbmapp_avaliacoes")
public class VbMapp extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "vbmapp_id")
    private UUID vbMappId;

    @Column(name = "objetivo_documento", nullable = false)
    private String objetivoDocumento;

    @Column(name = "niveis_coleta", nullable = false, length = 500)
    private String niveisColeta;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public VbMapp(LocalDateTime createdAt, UUID vbMappId, String objetivoDocumento, String niveisColeta, Aprendiz aprendiz) {
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
}
