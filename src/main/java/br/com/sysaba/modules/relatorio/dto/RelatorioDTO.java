package br.com.sysaba.modules.relatorio.dto;

import br.com.sysaba.modules.relatorio.dto.portage.CabecalhoDTO;

import java.util.List;

public class RelatorioDTO {

    private String titulo;

    private CabecalhoDTO cabecalho;

    private List<ProfissionalDTO> profissional;

    private AprendizDTO aprendiz;

    private List<TreinamentoDTO> treinamentos;

    public RelatorioDTO() {
    }

    public RelatorioDTO(String titulo, CabecalhoDTO cabecario, List<ProfissionalDTO> profissional, AprendizDTO aprendiz, List<TreinamentoDTO> treinamentos) {
        this.titulo = titulo;
        this.cabecalho = cabecario;
        this.profissional = profissional;
        this.aprendiz = aprendiz;
        this.treinamentos = treinamentos;
    }

    public CabecalhoDTO getCabecalho() {
        return cabecalho;
    }

    public void setCabecalho(CabecalhoDTO cabecalho) {
        this.cabecalho = cabecalho;
    }

    public List<ProfissionalDTO> getProfissional() {
        return profissional;
    }

    public void setProfissional(List<ProfissionalDTO> profissional) {
        this.profissional = profissional;
    }

    public AprendizDTO getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(AprendizDTO aprendiz) {
        this.aprendiz = aprendiz;
    }

    public List<TreinamentoDTO> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<TreinamentoDTO> treinamentos) {
        this.treinamentos = treinamentos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
