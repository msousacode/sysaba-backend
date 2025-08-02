package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.repository.TenantableRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlvoImportRespository extends TenantableRepository<AlvoImport> {

    @Query("select a from AlvoImport a where a.encerrado = false")
    Page<AlvoImport> findAllAndIsEncerradoFalse(Pageable pageable);
}
