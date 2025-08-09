package br.com.sysaba.modules.alvo.dto;

import java.util.List;

public class AlvoEstrelasDTO {

    public AlvoEstrelasDTO() {
    }

    public List<MudancaDTO> mudancas;

    public List<MudancaDTO> getMudancas() {
        return mudancas;
    }

    public void setMudancas(List<MudancaDTO> mudancas) {
        this.mudancas = mudancas;
    }
}
