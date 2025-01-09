package br.com.sysaba.modules.avaliacoes.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappBarreira;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VBMappBarreiraRepository extends TenantableRepository<VbMappBarreira> {
    List<VbMappBarreira> findByAprendiz_aprendizId(UUID aprendizId);

    @Modifying
    @Query("delete from VbMappBarreira v where v.aprendiz.aprendizId = :aprendizId")
    void deleteByAprendiz_aprendizId(@Param("aprendizId") UUID aprendizId);

    @Query("select v from VbMappBarreira v where v.aprendiz.aprendizId = :aprendizId order by v.codigo asc")
    List<VbMappBarreira> findPontuacaoBarreiraColeta(@Param("aprendizId") UUID aprendizId);

}
