package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRespository extends TenantableRepository<Atendimento> {
}
