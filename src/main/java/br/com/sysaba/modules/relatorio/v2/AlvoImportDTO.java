package br.com.sysaba.modules.relatorio.v2;

import java.util.List;
import java.util.UUID;

import br.com.sysaba.modules.relatorio.dto.AnotacaoDTO;


public class AlvoImportDTO {
    
    private UUID alvoId;

    private String nomeAlvo;

    private String tag;

    private boolean concluido;

    private boolean encerrado;

    private Integer totalEstrelaPositiva;

    private Integer totalEstrelaNegativa;

    private List<AnotacaoDTO> anotacoes;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }

    public boolean isEncerrado() {
        return encerrado;
    }

    public void setEncerrado(boolean encerrado) {
        this.encerrado = encerrado;
    }

    public Integer getTotalEstrelaPositiva() {
        return totalEstrelaPositiva;
    }

    public void setTotalEstrelaPositiva(Integer totalEstrelaPositiva) {
        this.totalEstrelaPositiva = totalEstrelaPositiva;
    }

    public Integer getTotalEstrelaNegativa() {
        return totalEstrelaNegativa;
    }

    public void setTotalEstrelaNegativa(Integer totalEstrelaNegativa) {
        this.totalEstrelaNegativa = totalEstrelaNegativa;
    }

    public List<AnotacaoDTO> getAnotacoes() {
        return anotacoes;
    }

    public void setAnotacoes(List<AnotacaoDTO> anotacoes) {
        this.anotacoes = anotacoes;
    }

    
}
