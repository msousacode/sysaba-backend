package br.com.sysaba.modules.stripe;

public class StripeCustomersDTO {

    private String stripeCustomerId;

    private String createdCustomerSubscriptionId;

    private String status;

    private Double priceId;

    public StripeCustomersDTO(){}

    public StripeCustomersDTO(String stripeCustomerId, String createdCustomerSubscriptionId, String status, Double priceId) {
        this.stripeCustomerId = stripeCustomerId;
        this.createdCustomerSubscriptionId = createdCustomerSubscriptionId;
        this.status = status;
        this.priceId = priceId;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public String getCreatedCustomerSubscriptionId() {
        return createdCustomerSubscriptionId;
    }

    public void setCreatedCustomerSubscriptionId(String createdCustomerSubscriptionId) {
        this.createdCustomerSubscriptionId = createdCustomerSubscriptionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPriceId() {
        return priceId;
    }

    public void setPriceId(Double priceId) {
        this.priceId = priceId;
    }
}
