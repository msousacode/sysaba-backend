package br.com.sysaba.modules.avaliacoes.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappAvaliacao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VBMappRepository extends TenantableRepository<VbMappAvaliacao> {
    List<VbMappAvaliacao> findAllByAprendiz_aprendizIdAndAtivoIsTrue(UUID aprendizId);

    @Modifying
    @Query("update VbMappAvaliacao  v set v.ativo = false where v.vbMappId = :avaliacaoId")
    void deleteByVbmappId(@Param("avaliacaoId") UUID avaliacaoId);
}
