package br.com.sysaba.modules.avaliacoes.vbmapp;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappColetaDTO;
import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "vbmapp_coletas")
public class VbMappColeta extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "vbmapp_coleta_id")
    private UUID vbmappColetaId;

    @Column(name = "nivel_coleta", nullable = false)
    private int nivelColeta;

    @Column(name = "tipo", nullable = false)
    private int tipo;//TODO criar um enum depois para isso aqui.

    @Column(name = "pontuacao", nullable = false)
    private Double pontuacao;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vbmapp_id", nullable = false)
    private VbMappAvaliacao vbMapp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @Column(name = "coleta_id", nullable = false)
    private Integer coletaId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "criado_por_nome")
    private String criadoNome;

    public static VbMappColeta of(VbMappColetaDTO dto, VbMappAvaliacao vbMappAvaliacao, Aprendiz aprendiz, Usuario usuario) {
        VbMappColeta vbMappColeta = new VbMappColeta();

        Objects.requireNonNull(usuario);
        vbMappColeta.setCriadoPor(usuario.getUsuarioId());
        vbMappColeta.setCriadoNome(usuario.getFullName());
        vbMappColeta.setVbMapp(vbMappAvaliacao);
        vbMappColeta.setAprendiz(aprendiz);
        vbMappColeta.setNivelColeta(dto.getNivelColeta());
        vbMappColeta.setPontuacao(dto.getPontuacao());
        vbMappColeta.setTipo(dto.getTipo());
        vbMappColeta.setDataColeta(LocalDateTime.now());
        vbMappColeta.setColetaId(dto.getColetaId());
        vbMappColeta.setCodigo(dto.getCodigo());
        vbMappColeta.setDescricao(dto.getDescricao());

        return vbMappColeta;
    }

    public VbMappColeta() {
        super(LocalDateTime.now());
        this.setAtivo(true);
        this.setCreatedAt(LocalDateTime.now());
    }

    public VbMappColeta(LocalDateTime createdAt, UUID vbmappColetaId, int nivelColeta, int tipo, Double pontuacao, LocalDateTime dataColeta, VbMappAvaliacao vbMapp, Aprendiz aprendiz, Integer coletaId) {
        super(createdAt);
        this.vbmappColetaId = vbmappColetaId;
        this.nivelColeta = nivelColeta;
        this.tipo = tipo;
        this.pontuacao = pontuacao;
        this.dataColeta = dataColeta;
        this.vbMapp = vbMapp;
        this.aprendiz = aprendiz;
        this.coletaId = coletaId;
    }

    public UUID getVbmappColetaId() {
        return vbmappColetaId;
    }

    public void setVbmappColetaId(UUID vbmappColetaId) {
        this.vbmappColetaId = vbmappColetaId;
    }

    public int getNivelColeta() {
        return nivelColeta;
    }

    public void setNivelColeta(int nivelColeta) {
        this.nivelColeta = nivelColeta;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Double pontuacao) {
        this.pontuacao = pontuacao;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public VbMappAvaliacao getVbMapp() {
        return vbMapp;
    }

    public void setVbMapp(VbMappAvaliacao vbMapp) {
        this.vbMapp = vbMapp;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriadoNome() {
        return criadoNome;
    }

    public void setCriadoNome(String criadoNome) {
        this.criadoNome = criadoNome;
    }
}
