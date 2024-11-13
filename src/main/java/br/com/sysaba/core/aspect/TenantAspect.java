package br.com.sysaba.core.aspect;

import br.com.sysaba.core.enums.ContantsSystem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TenantAspect {

    @Autowired
    private TenantContext tenantContext;

    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.multiteam.core.repository.TenantableRepository+.find*(..))")
    public void beforeFindOfTenantableRepository(JoinPoint joinPoint) {
        entityManager.unwrap(Session.class).disableFilter(ContantsSystem.TENANT_FILTER_NAME);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        if (AnnotationUtils.getAnnotation(methodSignature.getMethod(), DisabledTenantFilter.class) == null) {
            entityManager
                    .unwrap(Session.class)
                    .enableFilter(ContantsSystem.TENANT_FILTER_NAME)
                    .setParameter(ContantsSystem.TENANT_PARAMETER_NAME, tenantContext.getTenantId());
        }
    }
}
