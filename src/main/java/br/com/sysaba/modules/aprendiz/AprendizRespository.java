package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AprendizRespository extends TenantableRepository<Aprendiz> {

    @Modifying
    @Query("update Aprendiz a set a.ativo = false where a.aprendizId = :id")
    void inativar(@Param("id") UUID id);

    Page<Aprendiz> findAllByAtivoIsTrue(Pageable pageable);

    @Query(value = "select count(1) from Aprendiz a where a.ativo = true")
    Integer findTotalAprendizes();
}
