package br.com.sysaba.modules.termo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TermoRepository extends JpaRepository<Termo, UUID> {
    Optional<Termo> findByUsuario_usuarioId(UUID usuarioId);
}
