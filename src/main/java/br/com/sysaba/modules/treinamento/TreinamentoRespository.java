package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreinamentoRespository extends TenantableRepository<Treinamento> {
    List<Treinamento> findByTenantId(UUID usuarioId);
}
