package br.com.sysaba.modules.avaliacoes.portage.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PortageColetaRepository extends TenantableRepository<PortageColeta> {

    @Query("select v from PortageColeta v where v.coletaId in :coletaIds")
    List<PortageColeta> findAllColetasRespondidas(@Param("coletaIds") List<Integer> coletasIds);

    List<PortageColeta> findByPortage_portageId(UUID portageId);

    @Modifying
    @Query("delete from PortageColeta v where v.portageColetaId = :portageColetaId")
    void deleteByVbmappColetaId(@Param("portageColetaId") UUID portageColetaId);

    @Query("select count(*) from PortageColeta v where v.portage.portageId = :portageId and v.resposta is not null")
    Integer findColetasRespondidas(@Param("portageId") UUID portageId);


    @Modifying
    @Query("update PortageColeta v set v.ativo = false where v.portage.portageId = :avaliacaoId")
    void deleteByPortageId(@Param("avaliacaoId") UUID avaliacaoId);
}
