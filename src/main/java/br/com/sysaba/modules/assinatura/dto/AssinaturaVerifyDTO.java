package br.com.sysaba.modules.assinatura.dto;

import br.com.sysaba.core.enums.TipoAssinaturaEnum;

public class AssinaturaVerifyDTO {
    
    private long diasRestantesTeste;
    
    private TipoAssinaturaEnum tipoAssinaturaEnum;
    
    public long getDiasRestantesTeste() {
        return diasRestantesTeste;
    }
    public void setDiasRestantesTeste(long diasRestantesTeste) {
        this.diasRestantesTeste = diasRestantesTeste;
    }
    public TipoAssinaturaEnum getTipoAssinaturaEnum() {
        return tipoAssinaturaEnum;
    }
    public void setTipoAssinaturaEnum(TipoAssinaturaEnum tipoAssinaturaEnum) {
        this.tipoAssinaturaEnum = tipoAssinaturaEnum;
    }
}
