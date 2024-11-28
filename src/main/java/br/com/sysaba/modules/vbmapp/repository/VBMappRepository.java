package br.com.sysaba.modules.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.vbmapp.VbMappAvaliacao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VBMappRepository extends TenantableRepository<VbMappAvaliacao> {
    List<VbMappAvaliacao> findAllByAprendiz_aprendizId(UUID aprendizId);
}
