package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.modules.alvo.dto.AlvoDTO;
import br.com.sysaba.modules.alvo.dto.AlvoImportDTO;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlvoService implements GenericService<Alvo, UUID> {

    private final AprendizService aprendizService;

    private final TreinamentoService treinamentoService;

    private final AlvoRespository alvoRespository;

    private final AlvoImportRespository alvoImportRespository;

    public AlvoService(AlvoRespository alvoRespository, TreinamentoService treinamentoService, AprendizService aprendizService, AlvoImportRespository alvoImportRespository) {
        this.alvoRespository = alvoRespository;
        this.treinamentoService = treinamentoService;
        this.aprendizService = aprendizService;
        this.alvoImportRespository = alvoImportRespository;
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

    public void deleteByAlvoId(UUID alvoId) {
        alvoRespository.deleteByAlvoId(alvoId);
    }

    public void importarObjetivos(AlvoImportDTO dto) {        
        UUID aprendizId = dto.getAprendizId();
        List<UUID> objetivos = dto.getObjetivos();

        Aprendiz aprendiz = aprendizService.findById(aprendizId);

        List<Alvo> alvos = alvoRespository.findAllByIds(objetivos);

        List<AlvoImport> alvosImport = new ArrayList<>();
        
        for (Alvo alvo : alvos) {
            AlvoImport alvoImport = new AlvoImport();
            alvoImport.setAprendiz(aprendiz);
            alvoImport.setNomeAlvo(alvo.getNomeAlvo());
            alvoImport.setTag(alvo.getTag());
            alvosImport.add(alvoImport);
            alvoImport.setConcluido(false);
            alvoImport.setEncerrado(false);
        }

        alvoImportRespository.saveAll(alvosImport);
    }

    public Page<AlvoImportDTO> findImportadosAll(Pageable pageable) {
        return alvoImportRespository.findAllAndIsEncerradoFalse(pageable)
                .map(i -> {
                    AlvoImportDTO dto = new AlvoImportDTO();
                    dto.setAlvoId(i.getAlvoId());
                    dto.setAprendizId(i.getAprendiz().getAprendizId());
                    dto.setNomeAlvo(i.getNomeAlvo());
                    dto.setTag(i.getTag());
                    dto.setConcluido(i.isConcluido());
                    dto.setEncerrado(i.isEncerrado());
                    return dto;
                });
    }
}
