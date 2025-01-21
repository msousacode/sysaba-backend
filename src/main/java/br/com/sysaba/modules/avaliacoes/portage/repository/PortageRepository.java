package br.com.sysaba.modules.avaliacoes.portage.repository;

import br.com.sysaba.core.repository.TenantableRepository;
import br.com.sysaba.modules.avaliacoes.portage.PortageAvaliacao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PortageRepository extends TenantableRepository<PortageAvaliacao> {
    List<PortageAvaliacao> findAllByAprendiz_aprendizIdAndAtivoIsTrue(UUID aprendizId);

    @Modifying
    @Query("update PortageAvaliacao v set v.ativo = false where v.portageId = :avaliacaoId")
    void deleteAllByPortageId(@Param("avaliacaoId") UUID avaliacaoId);
}
