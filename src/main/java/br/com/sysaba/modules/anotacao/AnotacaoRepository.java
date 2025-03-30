package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AnotacaoRepository extends TenantableRepository<Anotacao> {
    Page<Anotacao> findByAtendimento_atendimentoIdAndTreinamento_treinamentoId(UUID atendimentoId, UUID treinamentoId, Pageable pageable);

    @Modifying
    @Query("delete from Anotacao a where a.anotacaoId = :id")
    void deleteByAnotacaoId(UUID id);
}
