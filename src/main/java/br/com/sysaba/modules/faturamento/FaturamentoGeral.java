package br.com.sysaba.modules.faturamento;

import br.com.sysaba.core.models.Tenantable;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "faturamento")
public class FaturamentoGeral extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "faturamento_id")
    private UUID faturamentoId;

    @Column(name = "profissional_id")
    private UUID profissionalId;

    @Column(name = "nome_profissional")
    private String nomeProfissional;

    @Column(name = "cargo_profissional")
    private String cargoProfissional;

    @Column(name = "preco")
    private String preco;

    @Column(name = "aprendiz_id")
    private UUID aprendizId;

    @Column(name = "nome_aprendiz")
    private String nomeAprendiz;

    @Column(name = "ausencia_justificada")
    private Boolean ausenciaJustificada;

    @Column(name = "descricao_justificada")
    private String descricaoJustificativa;

    @Column(name = "presente")
    private Boolean presente;

    @Column(name = "dia")
    private Integer dia;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private PagamentoEnum situacao;

    public FaturamentoGeral() {
        super(LocalDateTime.now());
    }

    public FaturamentoGeral(LocalDateTime createdAt) {
        super(createdAt);
    }

    public UUID getProfissionalId() {
        return profissionalId;
    }

    public void setProfissionalId(UUID profissionalId) {
        this.profissionalId = profissionalId;
    }

    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    public String getCargoProfissional() {
        return cargoProfissional;
    }

    public void setCargoProfissional(String cargoProfissional) {
        this.cargoProfissional = cargoProfissional;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public Boolean getAusenciaJustificada() {
        return ausenciaJustificada;
    }

    public void setAusenciaJustificada(Boolean ausenciaJustificada) {
        this.ausenciaJustificada = ausenciaJustificada;
    }

    public String getDescricaoJustificativa() {
        return descricaoJustificativa;
    }

    public void setDescricaoJustificativa(String descricaoJustificativa) {
        this.descricaoJustificativa = descricaoJustificativa;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public UUID getFaturamentoId() {
        return faturamentoId;
    }

    public void setFaturamentoId(UUID faturamentoId) {
        this.faturamentoId = faturamentoId;
    }

    public PagamentoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(PagamentoEnum situacao) {
        this.situacao = situacao;
    }
}
