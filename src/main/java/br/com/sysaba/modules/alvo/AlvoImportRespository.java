package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.repository.TenantableRepository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlvoImportRespository extends TenantableRepository<AlvoImport> {

    @Query("select a from AlvoImport a where a.encerrado = false and a.aprendiz.aprendizId = :aprendizId")
    Page<AlvoImport> findAllAndIsEncerradoFalse(Pageable pageable, UUID aprendizId);

    @Modifying
    @Query("update AlvoImport a set a.totalEstrelaPositiva = a.totalEstrelaPositiva + :quantidade where a.alvoId = :alvoId")
    void updateEstrelaPositiva(UUID alvoId, Integer quantidade);

    @Modifying
    @Query("update AlvoImport a set a.totalEstrelaNegativa = a.totalEstrelaNegativa + :quantidade where a.alvoId = :alvoId")
    void updateEstrelaNegativa(UUID alvoId, Integer quantidade);

    @Modifying
    @Query("update AlvoImport a set a.encerrado = true where a.aprendiz.aprendizId = :id")
    void encerrar(UUID id);
}
