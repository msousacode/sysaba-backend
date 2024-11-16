package br.com.sysaba.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface TenantableRepository<T> extends JpaRepository<T, UUID> {

    //Optional<T> findOneById(UUID id);
}