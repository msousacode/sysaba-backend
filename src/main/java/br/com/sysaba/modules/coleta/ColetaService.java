package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.modules.coleta.dto.RespostaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    public Page<Coleta> findByTreinamentoTreinamentoId(UUID treinamentoId, Pageable pageble) {
        return coletaRepository.findByTreinamento_treinamentoId(treinamentoId, pageble);
    }

    public void updateResposta(List<RespostaDTO> respostasDTOS) {
        List<Coleta> coletas = new ArrayList<>();

        respostasDTOS.forEach(i -> {
            Coleta coleta = findById(i.getUuid());
            coleta.setResposta(i.getResposta());
            coleta.setDataColeta(LocalDateTime.now());
            coleta.setFoiRespondido(true);
            coletas.add(coleta);
        });

        coletaRepository.saveAll(coletas);
    }

    public Coleta findColetaId(UUID coletaId) {
        return coletaRepository.findByColetaId(coletaId);
    }
}
