package br.com.sysaba.modules.treinamento.alvo;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlvoRespository extends TenantableRepository<Alvo> {
}
