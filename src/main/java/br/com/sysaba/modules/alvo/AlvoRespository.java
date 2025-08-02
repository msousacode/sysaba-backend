package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.repository.TenantableRepository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlvoRespository extends TenantableRepository<Alvo> {
    
    @Modifying
    @Query("delete from Alvo a where a.alvoId = :alvoId")
    public void deleteByAlvoId(UUID alvoId);

    @Query("select a from Alvo a where a.alvoId in :ids")
    public List<Alvo> findAllByIds(List<UUID> ids);
}
