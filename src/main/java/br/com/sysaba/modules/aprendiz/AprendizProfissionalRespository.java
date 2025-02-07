package br.com.sysaba.modules.aprendiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AprendizProfissionalRespository extends JpaRepository<AprendizProfissional, UUID> {
    List<AprendizProfissional> findAllByAprendiz_aprendizId(UUID usuarioId);

    List<AprendizProfissional> findAllByProfissional_usuarioId(UUID usuarioId);

    @Query("select count(a) from AprendizProfissional a where a.aprendiz.aprendizId = :aprendizId and a.profissional.usuarioId = :usuarioId")
    Integer verificaSeExisteVinculo(UUID aprendizId, UUID usuarioId);
}
