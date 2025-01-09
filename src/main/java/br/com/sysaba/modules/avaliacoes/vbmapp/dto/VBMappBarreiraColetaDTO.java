package br.com.sysaba.modules.avaliacoes.vbmapp.dto;

import java.util.List;

public class VBMappBarreiraColetaDTO {
    private List<VbMappBarreiraDTO> coletas;

    public List<VbMappBarreiraDTO> getColetas() {
        return coletas;
    }

    public void setColetas(List<VbMappBarreiraDTO> coletas) {
        this.coletas = coletas;
    }
}
