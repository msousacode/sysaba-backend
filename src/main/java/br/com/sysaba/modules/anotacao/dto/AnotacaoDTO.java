package br.com.sysaba.modules.anotacao.dto;

import br.com.sysaba.modules.anotacao.Anotacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class AnotacaoDTO {

    @JsonProperty("uuid")
    private UUID anotacaoId;

    @JsonProperty("coletaId")
    private UUID coletaId;

    @JsonProperty("atendimentoId")
    private UUID atendimentoId;

    @JsonProperty("treinamentoId")
    private UUID treinamentoId;

    @JsonProperty("data_anotacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataAnotacao;

    @JsonProperty("anotacao")
    private String anotacao;

    @JsonProperty("ativo")
    private boolean ativo;

    @JsonProperty("imprimirRelatorio")
    private boolean imprimirRelatorio;

    public AnotacaoDTO() {
        this.ativo = true;
        this.dataAnotacao = LocalDate.now();
    }

    public AnotacaoDTO(UUID anotacaoId, UUID coletaId, UUID treinamentoId, LocalDate dataAnotacao, String anotacao, boolean ativo, boolean imprimirRelatorio) {
        this.anotacaoId = anotacaoId;
        this.coletaId = coletaId;
        this.atendimentoId = treinamentoId;
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

    public UUID getAtendimentoId() {
        return atendimentoId;
    }

    public void setAtendimentoId(UUID atendimentoId) {
        this.atendimentoId = atendimentoId;
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

    public static AnotacaoDTO fromAnotacaoDTO(Anotacao anotacao) {
        AnotacaoDTO anotacaoDTO = new AnotacaoDTO();
        anotacaoDTO.setDataAnotacao(anotacao.getDataAnotacao());
        anotacaoDTO.setAnotacao(anotacao.getAnotacao());
        anotacaoDTO.setAtendimentoId(anotacao.getAtendimento().getAtendimentoId());
        anotacaoDTO.setAnotacaoId(anotacao.getAnotacaoId());
        anotacaoDTO.setAtivo(anotacao.getAtivo());
        anotacaoDTO.setColetaId(anotacao.getColeta().getColetaId());

        return anotacaoDTO;
    }

    public UUID getTreinamentoId() {
        return treinamentoId;
    }

    public void setTreinamentoId(UUID treinamentoId) {
        this.treinamentoId = treinamentoId;
    }

    public boolean isImprimirRelatorio() {
        return imprimirRelatorio;
    }

    public void setImprimirRelatorio(boolean imprimirRelatorio) {
        this.imprimirRelatorio = imprimirRelatorio;
    }
}
