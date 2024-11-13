package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.commons.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "aprendizes")
public class Aprendiz extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "aprendiz_id")
    private UUID aprendizId;

    @Column(name = "data_anotacao", nullable = false)
    private String dataAnotacao;

    @Column(name = "anotacao", nullable = false, length = 500)
    private String anotacao;

    public Aprendiz(LocalDateTime createdAt, UUID aprendizId, String dataAnotacao, String anotacao) {
        super(createdAt);
        this.aprendizId = aprendizId;
        this.dataAnotacao = dataAnotacao;
        this.anotacao = anotacao;
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    }

    public String getDataAnotacao() {
        return dataAnotacao;
    }

    public void setDataAnotacao(String dataAnotacao) {
        this.dataAnotacao = dataAnotacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
