package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.repository.TenantableRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AnotacaoRepository extends TenantableRepository<Anotacao> {
    
    @Modifying
    @Query("delete from Anotacao a where a.anotacaoId = :id")
    void deleteByAnotacaoId(UUID id);

    Page<Anotacao> findByAprendiz_aprendizIdAndEncerradoIsFalse(UUID aprendizId, PageRequest of);

    @Modifying
    @Query("update Anotacao a set a.encerrado = true where a.aprendiz.aprendizId = :id")
    void encerrarAnotacoes(UUID id);
}
