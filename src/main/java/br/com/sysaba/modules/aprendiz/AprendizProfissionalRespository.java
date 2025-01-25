package br.com.sysaba.modules.aprendiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AprendizProfissionalRespository extends JpaRepository<AprendizProfissional, UUID> {
    List<AprendizProfissional> findAllByProfissional_usuarioId(UUID usuarioId);
}
