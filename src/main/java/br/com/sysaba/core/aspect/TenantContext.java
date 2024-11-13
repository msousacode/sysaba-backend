package br.com.sysaba.core.aspect;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TenantContext {

    public UUID getTenantId() {
        return UUID.randomUUID();
    }
}