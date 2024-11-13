package br.com.sysaba.core.commons;

import br.com.sysaba.core.aspect.TenantContext;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Autowired;

public class TenantEntityListener {

    @Autowired
    private TenantContext tenantContext;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate(Object object) {
        if (object instanceof BaseEntity) {
            ((BaseEntity) object).setTenantId(tenantContext.getTenantId());
        }
    }

    @PreRemove
    public void preRemove(Object object) {
        if (object instanceof BaseEntity && ((BaseEntity) object).getTenantId() != tenantContext.getTenantId()) {
            throw new EntityNotFoundException();
        }
    }
}
