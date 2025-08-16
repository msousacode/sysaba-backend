package br.com.sysaba.modules.relatorio.v2;

import java.util.List;

public class RelatorioV2DTO {
    private List<AlvoImportDTO> alvos;    

    public List<AlvoImportDTO> getAlvos() {
        return alvos;
    }

    public void setAlvos(List<AlvoImportDTO> alvos) {
        this.alvos = alvos;
    }    
}
