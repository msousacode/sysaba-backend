package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.commons.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnotacaoService implements GenericService<Anotacao, UUID> {

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoService(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    @Override
    public JpaRepository<Anotacao, UUID> getRepository() {
        return anotacaoRepository;
    }

    @Override
    public Anotacao findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Anotacao save(Anotacao entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Anotacao update(UUID id, Anotacao entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Anotacao> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }

    public Page<Anotacao> findByAtendimento_atendimentoId(UUID atendimentoId, UUID treinamentoId, Pageable pageable) {
        return anotacaoRepository.findByAtendimento_atendimentoIdAndTreinamento_treinamentoId(atendimentoId, treinamentoId, pageable);
    }

    public void deleteById(UUID id) {
        anotacaoRepository.deleteByAnotacaoId(id);
    }
}
