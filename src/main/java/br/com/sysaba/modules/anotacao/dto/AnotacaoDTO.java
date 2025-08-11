package br.com.sysaba.modules.anotacao.dto;

import br.com.sysaba.modules.anotacao.Anotacao;

import br.com.sysaba.modules.usuario.UsuarioService;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

public class AnotacaoDTO {

    @JsonProperty("uuid")
    private UUID anotacaoId;

    @JsonProperty("aprendizId")
    private UUID aprendizId;

    @JsonProperty("coletaId")
    private UUID coletaId;

    @JsonProperty("data_anotacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAnotacao;

    @JsonProperty("anotacao")
    private String anotacao;

    @JsonProperty("ativo")
    private boolean ativo;

    @JsonProperty("imprimirRelatorio")
    private boolean imprimirRelatorio;

    @Column(name = "criado_por_nome")
    private String criadoNome;

    @Column(name = "read_online")
    private Boolean isReadonline;

    public AnotacaoDTO() {
        this.ativo = true;
        this.dataAnotacao = LocalDate.now();
    }

    public AnotacaoDTO(UUID anotacaoId, UUID coletaId, LocalDate dataAnotacao, String anotacao, boolean ativo, boolean imprimirRelatorio) {
        this.anotacaoId = anotacaoId;
        this.coletaId = coletaId;        
        this.dataAnotacao = dataAnotacao;
        this.anotacao = anotacao;
        this.ativo = ativo;
        this.imprimirRelatorio = imprimirRelatorio;
    }

    public UUID getAnotacaoId() {
        return anotacaoId;
    }

    public void setAnotacaoId(UUID anotacaoId) {
        this.anotacaoId = anotacaoId;
    }

    public UUID getColetaId() {
        return coletaId;
    }

    public void setColetaId(UUID coletaId) {
        this.coletaId = coletaId;
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public static AnotacaoDTO fromAnotacaoDTO(Anotacao anotacao, UUID criadoPor) {
        AnotacaoDTO anotacaoDTO = new AnotacaoDTO();
        anotacaoDTO.setDataAnotacao(anotacao.getDataAnotacao());
        anotacaoDTO.setAnotacao(anotacao.getAnotacao());        
        anotacaoDTO.setAnotacaoId(anotacao.getAnotacaoId());
        anotacaoDTO.setAtivo(anotacao.getAtivo());        
        anotacaoDTO.setCriadoNome(anotacao.getCriadoNome());

        if(criadoPor.equals(anotacao.getCriadoPor())) {
            anotacaoDTO.setIsReadonline(false);
        } else {
            anotacaoDTO.setIsReadonline(true);
        }

        return anotacaoDTO;
    }

    public boolean isImprimirRelatorio() {
        return imprimirRelatorio;
    }

    public void setImprimirRelatorio(boolean imprimirRelatorio) {
        this.imprimirRelatorio = imprimirRelatorio;
    }

    public String getCriadoNome() {
        return criadoNome;
    }

    public void setCriadoNome(String criadoNome) {
        this.criadoNome = criadoNome;
    }

    public Boolean getIsReadonline() {
        return isReadonline;
    }

    public void setIsReadonline(Boolean isReadonline) {
        this.isReadonline = isReadonline;
    }

    public UUID getAprendizId() {
        return aprendizId;
    }

    public void setAprendizId(UUID aprendizId) {
        this.aprendizId = aprendizId;
    } 
}
