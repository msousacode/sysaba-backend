package br.com.sysaba.modules.stripe;

public class StripeSubscriptionDTO {

    private String stripeCustomerId;
    private String stripeSubscriptionId;
    private String stripeSubscriptionStatus;
    private String stripePriceId;
    private String email;

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public String getStripeSubscriptionId() {
        return stripeSubscriptionId;
    }

    public void setStripeSubscriptionId(String stripeSubscriptionId) {
        this.stripeSubscriptionId = stripeSubscriptionId;
    }

    public String getStripeSubscriptionStatus() {
        return stripeSubscriptionStatus;
    }

    public void setStripeSubscriptionStatus(String stripeSubscriptionStatus) {
        this.stripeSubscriptionStatus = stripeSubscriptionStatus;
    }

    public String getStripePriceId() {
        return stripePriceId;
    }

    public void setStripePriceId(String stripePriceId) {
        this.stripePriceId = stripePriceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
