package br.com.sysaba.modules.vbmapp.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.vbmapp.VbMappColeta;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Repository
public interface VBMappColetaRepository extends TenantableRepository<VbMappColeta> {

    @Query("select v from VbMappColeta v where v.coletaId in :coletaIds")
    List<VbMappColeta> findAllColetasRespondidas(@Param("coletaIds") List<Integer> coletaIds);

    @Modifying
    @Query("delete from VbMappColeta v where v.vbmappColetaId = :vbmappColetaId")
    void deleteByVbmappColetaId(UUID vbmappColetaId);
}
