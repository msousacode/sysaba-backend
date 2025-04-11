package br.com.sysaba.modules.faturamento;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FaturamentoRepository extends TenantableRepository<FaturamentoGeral> {

    List<FaturamentoGeral> findAll(Specification<FaturamentoGeral> spec);

    @Query(value = """
            SELECT f.aprendizId, f.nomeAprendiz, SUM(CAST(REPLACE(f.preco, ',', '.') AS double)),
            f.situacao, f.mes, f.ano
            FROM FaturamentoGeral f
            WHERE f.ativo = true AND f.situacao = 'PENDENTE' AND (f.ausenciaJustificada = false OR f.ausenciaJustificada is null)
            GROUP BY f.aprendizId, f.nomeAprendiz, f.situacao, f.mes, f.ano            
            """)
    List<Object[]> findSomatorioPrecosGeral();

    @Query(value = """
            SELECT SUM(CAST(REPLACE(f.preco, ',', '.') AS double)), f.mes, f.ano
            FROM FaturamentoGeral f
            WHERE f.ativo = true AND f.situacao = 'PENDENTE' AND (f.ausenciaJustificada = false OR f.ausenciaJustificada is null)
            and f.mes = :mes and f.ano = :ano
            GROUP BY f.situacao, f.mes, f.ano            
            """)
    List<Object[]> findSomatorioTotalAReceber(Integer mes, Integer ano);

    @Query(value = """
            SELECT SUM(CAST(REPLACE(f.preco, ',', '.') AS double)), f.mes, f.ano
            FROM FaturamentoGeral f
            WHERE f.ativo = true
            AND f.situacao = 'PAGO'
            and f.mes = :mes 
            and f.ano = :ano
            GROUP BY f.situacao, f.mes, f.ano            
            """)
    List<Object[]> findSomatorioTotalPago(Integer mes, Integer ano);

    @Query(value = """
            SELECT count(1)
            FROM FaturamentoGeral f
            WHERE f.ativo = true
            and f.presente = true
            and f.mes = :mes 
            and f.ano = :ano                        
            """)
    Integer findSomatorioSessoesRealizadas(Integer mes, Integer ano);

    @Query(value = """
            SELECT count(1)
            FROM FaturamentoGeral f
            WHERE f.ativo = true
            and f.ausenciaJustificada = true                        
            and f.mes = :mes 
            and f.ano = :ano                        
            """)
    Integer findSomatorioAusenciasJustificadas(Integer mes, Integer ano);

    @Query(value = """
            SELECT count(1)
            FROM FaturamentoGeral f
            WHERE f.ativo = true
            and f.ausenciaJustificada = false
            and f.presente <> true                        
            and f.mes = :mes 
            and f.ano = :ano                        
            """)
    Integer findSomatorioAusenciasNaoJustificadas(Integer mes, Integer ano);

    @Query(value = """
            select count(1) from FaturamentoGeral f 
            where f.aprendizId = :aprendizId 
            and f.ativo = true 
            and f.presente = true 
            and f.ausenciaJustificada = false
            and f.mes = :mes
            and f.ano = :ano
            """)
    Integer findCountSessoesRealizadasPorAprendizId(UUID aprendizId, Integer mes, Integer ano);

    @Query(value = """
            select count(1) from FaturamentoGeral f 
            where f.aprendizId = :aprendizId 
            and f.ativo = true 
            and f.presente = false
            and f.ausenciaJustificada = false
            and f.mes = :mes
            and f.ano = :ano
            """)
    Integer findCountAuenciasNaoJustificadasPorAprendizId(UUID aprendizId, Integer mes, Integer ano);

    @Query(value = """
            select count(1) from FaturamentoGeral f 
            where f.aprendizId = :aprendizId 
            and f.ativo = true 
            and f.presente = false 
            and f.ausenciaJustificada = true
            and f.mes = :mes
            and f.ano = :ano
            """)
    Integer findCountAusenciasJustificadasPorAprendizId(UUID aprendizId, Integer mes, Integer ano);
}
