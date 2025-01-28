package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AbllsColetaRepository extends TenantableRepository<AbllsColeta> {

    @Query("select v from AbllsColeta v where v.coletaId in :coletasIds")
    List<AbllsColeta> findAllColetasRespondidas(List<Integer> coletasIds);

    @Modifying
    @Query("delete from AbllsColeta v where v.coletaId in :coletasIds")
    void deleteAllByColetaId(List<Integer> coletasIds);

    List<AbllsColeta> findByAblls_abllsId(UUID abllsId);
}
