package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.models.Tenantable;
import br.com.sysaba.modules.alvo.AlvoImport;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "anotacoes")
public class Anotacao extends Tenantable {

    @Id
    @GeneratedValue
    @Column(name = "anotacao_id")
    private UUID anotacaoId;

    @Column(name = "data_anotacao", nullable = false)
    private LocalDate dataAnotacao;

    @Column(name = "anotacao", nullable = false, length = 1000)
    private String anotacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alvo_import_id", nullable = false)
    private AlvoImport alvoImport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aprendiz_id", nullable = false)
    private Aprendiz aprendiz;

    @Column(name = "imprimir_relatorio")
    private Boolean imprimirRelatorio;

    @Column(name = "criado_por_nome")
    private String criadoNome;

    public Anotacao() {
        super(LocalDateTime.now());
        this.imprimirRelatorio = true;
    }

    public Anotacao(LocalDateTime createdAt, UUID anotacaoId, LocalDate dataAnotacao, String anotacao, Boolean imprimirRelatorio) {
        super(createdAt);
        this.anotacaoId = anotacaoId;
        this.dataAnotacao = dataAnotacao;
        this.anotacao = anotacao;    
        this.imprimirRelatorio = imprimirRelatorio;
    }

    public UUID getAnotacaoId() {
        return anotacaoId;
    }

    public void setAnotacaoId(UUID anotacaoId) {
        this.anotacaoId = anotacaoId;
    }

    public LocalDate getDataAnotacao() {
        return dataAnotacao;
    }

    public void setDataAnotacao(LocalDate dataAnotacao) {
        this.dataAnotacao = dataAnotacao;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }


    public Boolean getImprimirRelatorio() {
        return imprimirRelatorio;
    }

    public void setImprimirRelatorio(Boolean imprimirRelatorio) {
        this.imprimirRelatorio = imprimirRelatorio;
    }

    public String getCriadoNome() {
        return criadoNome;
    }

    public void setCriadoNome(String criadoNome) {
        this.criadoNome = criadoNome;
    }

    public AlvoImport getAlvoImport() {
        return alvoImport;
    }

    public void setAlvoImport(AlvoImport alvoImport) {
        this.alvoImport = alvoImport;
    }

    public Aprendiz getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(Aprendiz aprendiz) {
        this.aprendiz = aprendiz;
    }   
}
