package br.com.sysaba.core.aspect;

import br.com.sysaba.core.security.config.TenantAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TenantContext {

    public UUID getTenantId() {
        return ((TenantAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getTenantId();
    }
}