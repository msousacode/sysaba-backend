package br.com.sysaba.core.models;

import br.com.sysaba.core.commons.BaseEntity;
import br.com.sysaba.core.commons.TenantEntityListener;
import br.com.sysaba.core.enums.ContantsSystem;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;


import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@FilterDef(name = ContantsSystem.TENANT_FILTER_NAME,
        parameters = @ParamDef(name = ContantsSystem.TENANT_PARAMETER_NAME, type = UUID.class),
        defaultCondition = ContantsSystem.TENANT_COLUMN_NAME + " = :" + ContantsSystem.TENANT_PARAMETER_NAME)
@Filter(name = ContantsSystem.TENANT_FILTER_NAME)
@EntityListeners(TenantEntityListener.class)
public class Tenantable extends BaseEntity {

    @JsonIgnore
    @Column(name = "tenant_id", nullable = false)
    private UUID tenantId;

    public Tenantable(LocalDateTime createdAt) {
        super(createdAt);
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }
}
