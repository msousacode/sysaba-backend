package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.core.repository.TenantableRepository;

import java.util.List;
import java.util.UUID;

public interface AbllsRespository extends TenantableRepository<AbllsAvaliacao> {
    List<AbllsAvaliacao> findAllByAprendiz_aprendizId(UUID aprendizId);
}
