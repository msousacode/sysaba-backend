package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.commons.service.GenericService;

import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtendimentoService implements GenericService<Atendimento, UUID> {

    private final AtendimentoRespository atendimentoRepository;

    public AtendimentoService(AtendimentoRespository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }
    @Override
    public JpaRepository<Atendimento, UUID> getRepository() {
        return atendimentoRepository;
    }

    @Override
    public Atendimento findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Atendimento save(Atendimento entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Atendimento update(UUID id, Atendimento entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Atendimento> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }

    public List<Atendimento> findAllAtendimentosTreinamentosByAprendizId(UUID aprendizId) {
        List<Atendimento> atendimentos = atendimentoRepository.findAllByAprendiz_aprendizIdAndAtivoTrue(aprendizId);
        if(atendimentos.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizado treinamentos para o aprendiz");
        }
        return atendimentos;
    }

    public Atendimento findByAprendiz_aprendizId(UUID aprendizId) {
        return atendimentoRepository.findByAprendiz_aprendizId(aprendizId);
    }
}
