package br.com.sysaba.modules;

import br.com.sysaba.commons.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alvos")
public class Alvo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "alvo_id")
    private UUID alvoId;

    @Column(name = "nome_alvo", nullable = false)
    private String nomeAlvo;

    @Column(name = "pergunta", nullable = false)
    private String pergunta;

    @Column(name = "descricao_alvo", nullable = false, length = 500)
    private String descricaoAlvo;

    @Column(name = "repetir", nullable = false)
    private int repetir;

    @Column(name = "treinamento_uuid_fk", nullable = false)
    private String treinamentoUuidFk;

    @Column(name = "sync", nullable = false)
    private boolean sync;

    @Column(name = "identificador", nullable = false)
    private String identificador;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    public Alvo(LocalDateTime createdAt, String nomeAlvo, String pergunta, String descricaoAlvo, int repetir, String treinamentoUuidFk, boolean sync, String identificador, boolean ativo) {
        super(createdAt);
        this.nomeAlvo = nomeAlvo;
        this.pergunta = pergunta;
        this.descricaoAlvo = descricaoAlvo;
        this.repetir = repetir;
        this.treinamentoUuidFk = treinamentoUuidFk;
        this.sync = sync;
        this.identificador = identificador;
        this.ativo = ativo;
    }

    public UUID getAlvoId() {
        return alvoId;
    }

    public void setAlvoId(UUID alvoId) {
        this.alvoId = alvoId;
    }

    public String getNomeAlvo() {
        return nomeAlvo;
    }

    public void setNomeAlvo(String nomeAlvo) {
        this.nomeAlvo = nomeAlvo;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getDescricaoAlvo() {
        return descricaoAlvo;
    }

    public void setDescricaoAlvo(String descricaoAlvo) {
        this.descricaoAlvo = descricaoAlvo;
    }

    public int getRepetir() {
        return repetir;
    }

    public void setRepetir(int repetir) {
        this.repetir = repetir;
    }

    public String getTreinamentoUuidFk() {
        return treinamentoUuidFk;
    }

    public void setTreinamentoUuidFk(String treinamentoUuidFk) {
        this.treinamentoUuidFk = treinamentoUuidFk;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
