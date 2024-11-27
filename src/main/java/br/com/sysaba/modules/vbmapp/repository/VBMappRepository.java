package br.com.sysaba.modules.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.vbmapp.VbMappAvaliacao;
import org.springframework.stereotype.Repository;

@Repository
public interface VBMappRepository extends TenantableRepository<VbMappAvaliacao> {
}
