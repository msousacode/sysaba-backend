package br.com.sysaba.modules.relatorio.dto.pei;

import br.com.sysaba.modules.relatorio.dto.portage.CabecalhoDTO;

import java.util.ArrayList;
import java.util.List;

public class PEIRelatorioDTO {

    private String titulo;
    private CabecalhoDTO cabecario;
    private List<PEIDadoDTO> dados;
    private List<List<PEIDadoDTO>> dadosNiveisVbMapp = new ArrayList<>();

    public List<PEIDadoDTO> getDados() {
        return dados;
    }

    public void setDados(List<PEIDadoDTO> dados) {
        this.dados = dados;
    }

    public CabecalhoDTO getCabecario() {
        return cabecario;
    }

    public void setCabecario(CabecalhoDTO cabecario) {
        this.cabecario = cabecario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<List<PEIDadoDTO>> getDadosNiveisVbMapp() {
        return dadosNiveisVbMapp;
    }

    public void setDadosNiveisVbMapp(List<List<PEIDadoDTO>> dadosNiveisVbMapp) {
        this.dadosNiveisVbMapp = dadosNiveisVbMapp;
    }
}
