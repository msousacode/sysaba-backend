package br.com.sysaba.modules.avaliacoes.portage.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.portage.PortageAvaliacao;

import java.util.List;
import java.util.UUID;

public interface PortageRepository extends TenantableRepository<PortageAvaliacao> {
    List<PortageAvaliacao> findAllByAprendiz_aprendizId(UUID aprendizId);
}
