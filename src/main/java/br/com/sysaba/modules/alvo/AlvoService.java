package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.modules.alvo.dto.AlvoDTO;
import br.com.sysaba.modules.alvo.dto.AlvoEstrelasDTO;
import br.com.sysaba.modules.alvo.dto.AlvoImportDTO;
import br.com.sysaba.modules.alvo.enums.Estrela;
import br.com.sysaba.modules.anotacao.AnotacaoService;
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

    private final AnotacaoService anotacaoService;

    public AlvoService(AlvoRespository alvoRespository, TreinamentoService treinamentoService,
            AprendizService aprendizService, AlvoImportRespository alvoImportRespository, AnotacaoService anotacaoService) {
        this.alvoRespository = alvoRespository;
        this.treinamentoService = treinamentoService;
        this.aprendizService = aprendizService;
        this.alvoImportRespository = alvoImportRespository;
        this.anotacaoService = anotacaoService;
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
            alvoImport.setTotalEstrelaPositiva(0);
            alvoImport.setTotalEstrelaNegativa(0);
        }

        alvoImportRespository.saveAll(alvosImport);
    }

    public Page<AlvoImportDTO> findImportadosAll(Pageable pageable, UUID aprendizId) {
        return alvoImportRespository.findAllAndIsEncerradoFalse(pageable, aprendizId)
                .map(i -> {
                    AlvoImportDTO dto = new AlvoImportDTO();
                    dto.setAlvoId(i.getAlvoId());
                    dto.setAprendizId(i.getAprendiz().getAprendizId());
                    dto.setNomeAlvo(i.getNomeAlvo());
                    dto.setTag(i.getTag());
                    dto.setConcluido(i.isConcluido());
                    dto.setEncerrado(i.isEncerrado());
                    dto.setTotalEstrelaPositiva(i.getTotalEstrelaPositiva());
                    dto.setTotalEstrelaNegativa(i.getTotalEstrelaNegativa());
                    return dto;
                });
    }

    public void concluir(UUID id) {
        AlvoImport alvoImport = alvoImportRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alvo Import not found with id: " + id));

        boolean isConcluido = !alvoImport.isConcluido();
        alvoImport.setConcluido(isConcluido);
        alvoImportRespository.save(alvoImport);
    }

    public void atualizarEstrelas(AlvoEstrelasDTO dto) {
        dto.getMudancas().forEach(i -> {
            if (Estrela.POSITIVA.getTipo().equals(i.getTipo())) {                                
                alvoImportRespository.updateEstrelaPositiva(i.alvoId, i.quantidade);
            }

            if (Estrela.NEGATIVA.getTipo().equals(i.getTipo())) {
                alvoImportRespository.updateEstrelaNegativa(i.alvoId, i.quantidade);
            }
        });
    }

    public void encerrar(UUID id) {
        alvoImportRespository.encerrar(id);
        anotacaoService.encerrarAnotacoes(id);
    }
}
