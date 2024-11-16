package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AprendizRespository extends TenantableRepository<Aprendiz> {
}
