package br.com.sysaba.modules.cargo;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRespository extends TenantableRepository<Cargo> {
    List<Cargo> findAllByAtivoIsTrue();
}
