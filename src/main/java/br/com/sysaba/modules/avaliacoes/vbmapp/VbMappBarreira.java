package br.com.sysaba.modules.avaliacoes.vbmapp;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappBarreiraDTO;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappDTO;
import jakarta.persistence.*;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.swing.text.Utilities;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "vbmapp_barreiras")
public class VbMappBarreira extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "vbmapp_barreira_id")
    private UUID vbMappBarreiraId;

    @Column(name = "questao", nullable = false)
    private String questao;

    @Column(name = "resposta", nullable = false)
    private String resposta;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @Column(name = "codigo", nullable = false)
    private Integer codigo;

    public VbMappBarreira() {
        super(LocalDateTime.now());
    }

    public static VbMappBarreira getInstance(VbMappBarreiraDTO dto, Aprendiz aprendiz, UUID usuarioId) {
        Objects.requireNonNull(usuarioId);

        VbMappBarreira vbMappBarreira = new VbMappBarreira();

        vbMappBarreira.setAprendiz(aprendiz);
        vbMappBarreira.setDescricao(dto.getDescricao());
        vbMappBarreira.setQuestao(dto.getQuestao());
        vbMappBarreira.setResposta(dto.getResposta());
        vbMappBarreira.setCodigo(dto.getCodigo());
        vbMappBarreira.setCriadoPor(usuarioId);
        return vbMappBarreira;
    }

    public UUID getVbMappBarreiraId() {
        return vbMappBarreiraId;
    }

    public void setVbMappBarreiraId(UUID vbMappBarreiraId) {
        this.vbMappBarreiraId = vbMappBarreiraId;
    }

    public String getQuestao() {
        return questao;
    }

    public void setQuestao(String questao) {
        this.questao = questao;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
}
