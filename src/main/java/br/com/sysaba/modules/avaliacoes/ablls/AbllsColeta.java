package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsColetaDTO;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsHabilidadeEnum;
import br.com.sysaba.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ablls_coletas")
public class AbllsColeta extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "ablls_coleta_id")
    private UUID abllsColetaId;

    @Column(name = "habilidade", nullable = false)
    private AbllsHabilidadeEnum habilidade;

    @Column(name = "resposta", nullable = false)
    private Integer resposta;

    @Column(name = "data_coleta", nullable = false)
    private LocalDateTime dataColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ablls_id", nullable = false)
    private AbllsAvaliacao ablls;

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

    public AbllsColeta() {
        super(LocalDateTime.now());
    }

    public static AbllsColeta of(Aprendiz aprendiz, AbllsAvaliacao abllsAvaliacao, AbllsColetaDTO dto, Integer habilidade, Usuario usuario) {
        AbllsColeta ablls = new AbllsColeta();
        ablls.setAblls(abllsAvaliacao);
        ablls.setAprendiz(aprendiz);
        ablls.setColetaId(dto.getId());
        ablls.setCodigo(dto.getCodigo());
        ablls.setDescricao(dto.getDescricao());
        ablls.setResposta(dto.getResposta());
        ablls.setHabilidade(AbllsHabilidadeEnum.getByCod(habilidade));
        ablls.setDataColeta(LocalDateTime.now());
        ablls.setCriadoNome(usuario.getFullName());
        ablls.setAtivo(true);

        return ablls;
    }

    public UUID getAbllsColetaId() {
        return abllsColetaId;
    }

    public void setAbllsColetaId(UUID abllsColetaId) {
        this.abllsColetaId = abllsColetaId;
    }

    public AbllsHabilidadeEnum getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(AbllsHabilidadeEnum habilidade) {
        this.habilidade = habilidade;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }

    public LocalDateTime getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDateTime dataColeta) {
        this.dataColeta = dataColeta;
    }

    public AbllsAvaliacao getAblls() {
        return ablls;
    }

    public void setAblls(AbllsAvaliacao ablls) {
        this.ablls = ablls;
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
