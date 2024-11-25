package br.com.sysaba.modules.stripe;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "stripe_customers")
public class StripeCustomers {

    @Id
    @GeneratedValue
    @Column(name = "stripe_id")
    private UUID stripeId;

    private String stripeCustomerId;

    private String createdCustomerSubscriptionId;

    private String status;

    private double priceId;

    public StripeCustomers() {
    }

    public StripeCustomers(UUID stripeId, String stripeCustomerId, String createdCustomerSubscriptionId, String status, double priceId) {
        this.stripeId = stripeId;
        this.stripeCustomerId = stripeCustomerId;
        this.createdCustomerSubscriptionId = createdCustomerSubscriptionId;
        this.status = status;
        this.priceId = priceId;
    }

    public UUID getStripeId() {
        return stripeId;
    }

    public void setStripeId(UUID stripeId) {
        this.stripeId = stripeId;
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

    public double getPriceId() {
        return priceId;
    }

    public void setPriceId(double priceId) {
        this.priceId = priceId;
    }
}
