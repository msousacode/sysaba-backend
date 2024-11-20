package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlvoService implements GenericService<Alvo, UUID> {

    private final TreinamentoService treinamentoService;

    private final AlvoRespository alvoRespository;

    public AlvoService(AlvoRespository alvoRespository, TreinamentoService treinamentoService) {
        this.alvoRespository = alvoRespository;
        this.treinamentoService = treinamentoService;
    }

    @Override
    public JpaRepository<Alvo, UUID> getRepository() {
        return alvoRespository;
    }

    @Override
    public Alvo findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Alvo save(Alvo entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Alvo update(UUID id, Alvo entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Alvo> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }

    public List<Alvo> getAlvosByTreinamento(UUID treinamentoId) {
        return treinamentoService.findById(treinamentoId).getAlvos();
    }
}
