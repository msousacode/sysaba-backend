package br.com.sysaba.modules.avaliacoes.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappColeta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VBMappColetaRepository extends TenantableRepository<VbMappColeta> {

    @Query("select v from VbMappColeta v where v.coletaId in :coletaIds")
    List<VbMappColeta> findAllColetasRespondidas(@Param("coletaIds") List<Integer> coletaIds);

    @Modifying
    @Query("delete from VbMappColeta v where v.vbmappColetaId = :vbmappColetaId")
    void deleteByVbmappColetaId(UUID vbmappColetaId);

    List<VbMappColeta> findByAprendiz_aprendizId(UUID vbmappUuid);

    List<VbMappColeta> findByVbMapp_vbMappId(UUID vbmappUuid);

    @Query("select v from VbMappColeta v where v.vbMapp.vbMappId = :vbmappUuid")
    List<VbMappColeta> findPontuacaoColetaAvaliacao(UUID vbmappUuid);

    @Query("select count(*) from VbMappColeta v where v.vbMapp.vbMappId = :vbmappId and v.pontuacao is not null")
    Integer findColetasRespondidas(UUID vbmappId);

    @Modifying
    @Query("update VbMappColeta v set v.ativo = false where v.vbMapp.vbMappId = :avaliacaoId")
    void deleteAllByVbMappId(@Param("avaliacaoId") UUID avaliacaoId);
}
