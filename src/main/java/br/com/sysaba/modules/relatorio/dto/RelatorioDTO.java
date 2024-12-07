package br.com.sysaba.modules.relatorio.dto;

import java.util.List;

public class RelatorioDTO {

    private CabecarioDTO cabecario;

    private List<ProfissionalDTO> profissional;

    private AprendizDTO aprendiz;

    private List<TreinamentoDTO> treinamentos;

    public RelatorioDTO() {
    }

    public RelatorioDTO(CabecarioDTO cabecario, List<ProfissionalDTO> profissional, AprendizDTO aprendiz, List<TreinamentoDTO> treinamentos) {
        this.cabecario = cabecario;
        this.profissional = profissional;
        this.aprendiz = aprendiz;
        this.treinamentos = treinamentos;
    }

    public CabecarioDTO getCabecario() {
        return cabecario;
    }

    public void setCabecario(CabecarioDTO cabecario) {
        this.cabecario = cabecario;
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
}
