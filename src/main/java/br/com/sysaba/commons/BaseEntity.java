package br.com.sysaba.commons;

import br.com.sysaba.enums.ContantsSystem;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@FilterDef(name = ContantsSystem.TENANT_COLUMN_NAME,
        parameters = @ParamDef(name = ContantsSystem.TENANT_PARAMETER_NAME, type = UUID.class),
        defaultCondition = ContantsSystem.TENANT_COLUMN_NAME + " = :" + ContantsSystem.TENANT_PARAMETER_NAME)
@Filter(name = ContantsSystem.TENANT_FILTER_NAME, condition = "tenant_id = :tenantId")
@EntityListeners(TenantEntityListener.class)
public class BaseEntity {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Column(name = "tentant_id", nullable = false)
    private UUID tenantId;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public UUID getTenantId() {
        return tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.ativo = Boolean.TRUE;
    }

    public BaseEntity(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
