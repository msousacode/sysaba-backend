package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.repository.TenantableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AprendizRespository extends TenantableRepository<Aprendiz> {
}
