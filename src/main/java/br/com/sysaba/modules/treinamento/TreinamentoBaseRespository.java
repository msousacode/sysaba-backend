package br.com.sysaba.modules.treinamento;

import br.com.sysaba.modules.treinamento.base.TreinamentoBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TreinamentoBaseRespository extends JpaRepository<TreinamentoBase, UUID> {
}
