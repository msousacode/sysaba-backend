package br.com.sysaba.core.commons;

import br.com.sysaba.core.aspect.TenantContext;
import br.com.sysaba.core.models.Tenantable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TenantEntityListener {

    @Autowired
    private TenantContext tenantContext;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(Object object) {
        if (object instanceof Tenantable) {
            ((Tenantable) object).setTenantId(tenantContext.getTenantId());
        }
    }

    @PreRemove
    public void preRemove(Object object) {
        if (object instanceof BaseEntity && ((Tenantable) object).getTenantId() != tenantContext.getTenantId()) {
            throw new EntityNotFoundException();
        }
    }
}
