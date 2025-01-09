package br.com.sysaba.modules.avaliacoes.vbmapp;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    public VbMappBarreira() {
        super(LocalDateTime.now());
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
}
