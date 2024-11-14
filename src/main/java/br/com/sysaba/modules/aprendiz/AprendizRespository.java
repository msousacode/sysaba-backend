package br.com.sysaba.modules.aprendiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AprendizRespository extends JpaRepository<Aprendiz, UUID> {
}
