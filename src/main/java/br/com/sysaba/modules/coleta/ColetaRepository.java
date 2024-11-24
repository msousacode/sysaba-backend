package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColetaRepository extends TenantableRepository<Coleta> {
    Page<Coleta> findByTreinamento_treinamentoId(UUID treinamentoId, Pageable pageble);

    Coleta findByColetaId(UUID alvoId);
}
