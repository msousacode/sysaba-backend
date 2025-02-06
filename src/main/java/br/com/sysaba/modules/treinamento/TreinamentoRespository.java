package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreinamentoRespository extends TenantableRepository<Treinamento> {
    List<Treinamento> findByTenantId(UUID usuarioId);

    @Modifying
    @Query("update Treinamento t set t.ativo = false where t.treinamentoId = :treinamentoId")
    void deleteTreinamento(UUID treinamentoId);

    Page<Treinamento> findAllByAtivoIsTrue(PageRequest of);
}
