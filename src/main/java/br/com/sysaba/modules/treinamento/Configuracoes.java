package br.com.sysaba.modules.treinamento;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "treinamentos_config")
public class Configuracoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "configuracao_id")
    private UUID configuracaoId;

    @JoinColumn(name = "data_final")
    private LocalDate dataFinal;

    @Column(name = "repetir")
    private Integer repetir;

    @Column(name = "seg")
    private boolean seg;

    @Column(name = "ter")
    private boolean ter;

    @Column(name = "qua")
    private boolean qua;

    @Column(name = "qui")
    private boolean qui;

    @Column(name = "sex")
    private boolean sex;

    @Column(name = "sab")
    private boolean sab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treinamento_id")
    private Treinamento treinamento;

    public Configuracoes(){}

    public Configuracoes(UUID configuracaoId, LocalDate dataFinal, Integer repetir, boolean seg, boolean ter, boolean qua, boolean qui, boolean sex, boolean sab, Treinamento treinamento) {
        this.configuracaoId = configuracaoId;
        this.dataFinal = dataFinal;
        this.repetir = repetir;
        this.seg = seg;
        this.ter = ter;
        this.qua = qua;
        this.qui = qui;
        this.sex = sex;
        this.sab = sab;
        this.treinamento = treinamento;
    }

    public UUID getConfiguracaoId() {
        return configuracaoId;
    }

    public void setConfiguracaoId(UUID configuracaoId) {
        this.configuracaoId = configuracaoId;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getRepetir() {
        return repetir;
    }

    public void setRepetir(Integer repetir) {
        this.repetir = repetir;
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

    public Treinamento getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
