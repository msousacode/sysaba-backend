package br.com.sysaba.modules.faturamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaturamentoRepository extends TenantableRepository<FaturamentoGeral> {

    List<FaturamentoGeral> findAll(Specification<FaturamentoGeral> spec);

    @Query(value = """
            SELECT f.aprendizId, f.nomeAprendiz, SUM(CAST(REPLACE(f.preco, ',', '.') AS double)),
            f.situacao, f.mes, f.ano
            FROM FaturamentoGeral f
            WHERE f.ativo = true AND f.situacao = 'PENDENTE'
            GROUP BY f.aprendizId, f.nomeAprendiz, f.situacao, f.mes, f.ano            
            """)
    List<Object[]> findSomatorioPrecosPorAprendiz();
}
