package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColetaRepository extends TenantableRepository<Coleta> {
}
