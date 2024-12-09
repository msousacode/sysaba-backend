package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.portage.dto.PortageDTO;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "portage_avaliacoes")
public class PortageAvaliacao extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "portage_id")
    private UUID portageId;

    @Column(name = "objetivo_documento")
    private String objetivoDocumento;

    @Column(name = "idades_coleta", nullable = false)
    private String idadesColeta;

    @Column(name = "protocolo")
    private String protocolo;

    @ManyToOne
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public static PortageAvaliacao from(PortageDTO dto, Aprendiz aprendiz) {
        PortageAvaliacao vbMapp = new PortageAvaliacao();
        vbMapp.setAprendiz(aprendiz);
        vbMapp.setIdadesColeta(dto.getIdadesColeta());
        vbMapp.setObjetivoDocumento(dto.getObjetivo());
        vbMapp.setProtocolo(dto.getProtocolo());
        return vbMapp;
    }

    public PortageAvaliacao() {
        super(LocalDateTime.now());
        this.setAtivo(true);
    }

    public PortageAvaliacao(LocalDateTime createdAt, UUID portageId, String objetivoDocumento, String idadesColeta, String protocolo, Aprendiz aprendiz) {
        super(createdAt);
        this.portageId = portageId;
        this.objetivoDocumento = objetivoDocumento;
        this.idadesColeta = idadesColeta;
        this.protocolo = protocolo;
        this.aprendiz = aprendiz;
    }

    public UUID getPortageId() {
        return portageId;
    }

    public void setPortageId(UUID portageId) {
        this.portageId = portageId;
    }

    public String getObjetivoDocumento() {
        return objetivoDocumento;
    }

    public void setObjetivoDocumento(String objetivoDocumento) {
        this.objetivoDocumento = objetivoDocumento;
    }

    public String getIdadesColeta() {
        return idadesColeta;
    }

    public void setIdadesColeta(String idadesColeta) {
        this.idadesColeta = idadesColeta;
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
