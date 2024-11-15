package br.com.sysaba.core.security.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.UUID;

public class TenantAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final UUID tenantId;

    public TenantAuthenticationToken(Object principal, UUID tenantId) {
        super(null);
        setAuthenticated(true);
        this.principal = principal;
        this.tenantId = tenantId;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public UUID getTenantId() {
        return this.tenantId;
    }
}