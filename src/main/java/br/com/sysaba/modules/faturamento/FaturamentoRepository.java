package br.com.sysaba.modules.faturamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturamentoRepository extends TenantableRepository<FaturamentoGeral> {
    List<FaturamentoGeral> findAllByAtivoIsTrue();
}
