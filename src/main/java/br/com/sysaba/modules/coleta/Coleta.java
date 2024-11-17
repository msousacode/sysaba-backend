package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.treinamento.Alvo;
import br.com.sysaba.modules.treinamento.Treinamento;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "coletas")
public class Coleta extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "coleta_id")
    private UUID coletaId;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @Column(name = "resposta", nullable = false)
    private String resposta;

    @Column(name = "foi_respondido", nullable = false)
    private boolean foiRespondido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alvo_id", nullable = false)
    private Alvo alvo;

    @Column(name = "data_final_coleta", nullable = false)
    private LocalDateTime dataFinalColeta;

    @Column(name = "seg", nullable = false)
    private boolean seg;

    @Column(name = "ter", nullable = false)
    private boolean ter;

    @Column(name = "qua", nullable = false)
    private boolean qua;

    @Column(name = "qui", nullable = false)
    private boolean qui;

    @Column(name = "sex", nullable = false)
    private boolean sex;

    @Column(name = "sab", nullable = false)
    private boolean sab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id", nullable = false)
    private Treinamento treinamento;

    public Coleta() {
        super(LocalDateTime.now());
    }

    public Coleta(LocalDateTime createdAt, UUID coletaId, LocalDateTime dataColeta, String resposta, boolean foiRespondido, Alvo alvo, LocalDateTime dataFinalColeta, boolean seg, boolean ter, boolean qua, boolean qui, boolean sex, boolean sab, Aprendiz aprendiz, Treinamento treinamento) {
        super(createdAt);
        this.coletaId = coletaId;
        this.dataColeta = dataColeta;
        this.resposta = resposta;
        this.foiRespondido = foiRespondido;
        this.alvo = alvo;
        this.dataFinalColeta = dataFinalColeta;
        this.seg = seg;
        this.ter = ter;
        this.qua = qua;
        this.qui = qui;
        this.sex = sex;
        this.sab = sab;
        this.aprendiz = aprendiz;
        this.treinamento = treinamento;
    }

    public UUID getColetaId() {
        return coletaId;
    }

    public void setColetaId(UUID coletaId) {
        this.coletaId = coletaId;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public boolean isFoiRespondido() {
        return foiRespondido;
    }

    public void setFoiRespondido(boolean foiRespondido) {
        this.foiRespondido = foiRespondido;
    }

    public Alvo getAlvo() {
        return alvo;
    }

    public void setAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

    public LocalDateTime getDataFinalColeta() {
        return dataFinalColeta;
    }

    public void setDataFinalColeta(LocalDateTime dataFinalColeta) {
        this.dataFinalColeta = dataFinalColeta;
    }

    public boolean isSeg() {
        return seg;
    }

    public void setSeg(boolean seg) {
        this.seg = seg;
    }

    public boolean isTer() {
        return ter;
    }

    public void setTer(boolean ter) {
        this.ter = ter;
    }

    public boolean isQua() {
        return qua;
    }

    public void setQua(boolean qua) {
        this.qua = qua;
    }

    public boolean isQui() {
        return qui;
    }

    public void setQui(boolean qui) {
        this.qui = qui;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isSab() {
        return sab;
    }

    public void setSab(boolean sab) {
        this.sab = sab;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
