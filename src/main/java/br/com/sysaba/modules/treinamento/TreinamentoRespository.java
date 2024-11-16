package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinamentoRespository extends TenantableRepository<Treinamento> {
}
