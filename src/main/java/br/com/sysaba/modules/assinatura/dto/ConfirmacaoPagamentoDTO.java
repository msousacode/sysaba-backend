package br.com.sysaba.modules.assinatura.dto;

import java.util.Objects;

public class ConfirmacaoPagamentoDTO {

    private String email;
    private String invoiceId;
    private String customerId;
    private String stripeSubscriptionId;
    
    public ConfirmacaoPagamentoDTO(String email, String invoiceId, String customerId) {
        this.email = email;
        Objects.requireNonNull(invoiceId, "invoicedId não deve ser null");
        Objects.requireNonNull(customerId, "customerId não deve ser null");
        this.invoiceId = invoiceId;
        this.customerId = customerId;
    }
    
    public String getStripeSubscriptionId() {
        return stripeSubscriptionId;
    }

    public void setStripeSubscriptionId(String stripeSubscriptionId) {
        this.stripeSubscriptionId = stripeSubscriptionId;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
