package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AtendimentoRespository extends TenantableRepository<Atendimento> {
    List<Atendimento> findAllByAprendiz_aprendizIdAndAtivoTrue(UUID aprendizId);

    Atendimento findByAprendiz_aprendizId(UUID aprendizId);
}
