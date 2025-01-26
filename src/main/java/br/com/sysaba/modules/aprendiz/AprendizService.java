package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.commons.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AprendizService implements GenericService<Aprendiz, UUID> {

    private final AprendizRespository aprendizRespository;

    public AprendizService(AprendizRespository aprendizRespository) {
        this.aprendizRespository = aprendizRespository;
    }

    @Override
    public JpaRepository<Aprendiz, UUID> getRepository() {
        return aprendizRespository;
    }

    @Override
    public Aprendiz findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Aprendiz save(Aprendiz entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Aprendiz update(UUID id, Aprendiz entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Aprendiz> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    public Page<Aprendiz> findAllIsTrue(Pageable pageable) {
        return aprendizRespository.findAllByAtivoIsTrue(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }

    public void inativar(UUID id) {
        aprendizRespository.inativar(id);
    }
}
