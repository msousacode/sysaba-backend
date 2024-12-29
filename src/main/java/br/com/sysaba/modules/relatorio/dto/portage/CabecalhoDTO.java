package br.com.sysaba.modules.relatorio.dto.portage;

public class CabecalhoDTO {
    private String nomeAprendiz;
    private String nascimento;

    public CabecalhoDTO(){}

    public CabecalhoDTO(String nomeAprendiz, String nascimento) {
        this.nomeAprendiz = nomeAprendiz;
        this.nascimento = nascimento;
    }

    public String getNomeAprendiz() {
        return nomeAprendiz;
    }

    public void setNomeAprendiz(String nomeAprendiz) {
        this.nomeAprendiz = nomeAprendiz;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
}
