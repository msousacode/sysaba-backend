package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.commons.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TreinamentoService implements GenericService<Treinamento, UUID> {

    private final TreinamentoRespository treinamentoRespository;

    public TreinamentoService(TreinamentoRespository treinamentoRespository) {
        this.treinamentoRespository = treinamentoRespository;
    }

    @Override
    public JpaRepository<Treinamento, UUID> getRepository() {
        return treinamentoRespository;
    }

    @Override
    public Treinamento findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Treinamento save(Treinamento entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Treinamento update(UUID id, Treinamento entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Treinamento> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }
}
