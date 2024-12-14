package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.portage.dto.PortageColetaDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "portage_coletas")
public class PortageColeta extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "vbmapp_coleta_id")
    private UUID portageColetaId;

    @Column(name = "idade_coleta", nullable = false)
    private String idadeColeta;

    @Column(name = "resposta", nullable = false)
    private String resposta;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vbmapp_id", nullable = false)
    private PortageAvaliacao portage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @Column(name = "coleta_id", nullable = false)
    private Integer coletaId;

    public static PortageColeta of(PortageColetaDTO dto, PortageAvaliacao portageAvaliacao, Aprendiz aprendiz) {
        PortageColeta portageColeta = new PortageColeta();

        portageColeta.setPortage(portageAvaliacao);
        portageColeta.setAprendiz(aprendiz);
        portageColeta.setIdadeColeta(dto.getIdadeColeta());
        portageColeta.setResposta(dto.getResposta());
        portageColeta.setDataColeta(LocalDateTime.now());
        portageColeta.setColetaId(dto.getColetaId());

        return portageColeta;
    }

    public PortageColeta() {
        super(LocalDateTime.now());
        this.setAtivo(true);
        this.setCreatedAt(LocalDateTime.now());
    }

    public UUID getPortageColetaId() {
        return portageColetaId;
    }

    public void setPortageColetaId(UUID portageColetaId) {
        this.portageColetaId = portageColetaId;
    }

    public String getIdadeColeta() {
        return idadeColeta;
    }

    public void setIdadeColeta(String idadeColeta) {
        this.idadeColeta = idadeColeta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public PortageAvaliacao getPortage() {
        return portage;
    }

    public void setPortage(PortageAvaliacao portage) {
        this.portage = portage;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public Integer getColetaId() {
        return coletaId;
    }

    public void setColetaId(Integer coletaId) {
        this.coletaId = coletaId;
    }
}
