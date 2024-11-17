package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.commons.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ColetaService implements GenericService<Coleta, UUID> {

    private final ColetaRepository coletaRepository;

    public ColetaService(ColetaRepository coletaRepository) {
        this.coletaRepository = coletaRepository;
    }

    @Override
    public JpaRepository<Coleta, UUID> getRepository() {
        return coletaRepository;
    }

    @Override
    public Coleta findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Coleta save(Coleta entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Coleta update(UUID id, Coleta entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Coleta> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }
}
