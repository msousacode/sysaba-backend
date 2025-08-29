package br.com.sysaba.modules.assinatura.dto;

public class CancelamentoDTO {

    private String dataCancelamento;
    private String subscriptionId;

    public CancelamentoDTO(String dataCancelamento, String subscriptionId) {
        this.dataCancelamento = dataCancelamento;
        this.subscriptionId = subscriptionId;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
