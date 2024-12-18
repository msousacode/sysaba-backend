package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface AnotacaoRepository extends TenantableRepository<Anotacao> {
    Page<Anotacao> findByAtendimento_atendimentoId(UUID atendimentoId, Pageable pageable);
}
