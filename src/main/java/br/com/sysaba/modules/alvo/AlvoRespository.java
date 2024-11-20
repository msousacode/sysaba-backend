package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlvoRespository extends TenantableRepository<Alvo> {
}
