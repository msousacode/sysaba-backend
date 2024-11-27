package br.com.sysaba.modules.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.vbmapp.VbMappColeta;
import org.springframework.stereotype.Repository;

@Repository
public interface VBMappColetaRepository extends TenantableRepository<VbMappColeta> {
}
