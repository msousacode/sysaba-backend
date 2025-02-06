package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.alvo.AlvoRespository;
import br.com.sysaba.modules.treinamento.base.TreinamentoBase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TreinamentoService implements GenericService<Treinamento, UUID> {

    private final TreinamentoRespository treinamentoRespository;

    private final AlvoRespository alvoRespository;

    private final TreinamentoBaseRespository treinamentoBaseRespository;

    public TreinamentoService(TreinamentoRespository treinamentoRespository, AlvoRespository alvoRespository, TreinamentoBaseRespository treinamentoBaseRespository) {
        this.treinamentoRespository = treinamentoRespository;
        this.alvoRespository = alvoRespository;
        this.treinamentoBaseRespository = treinamentoBaseRespository;
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

    public void importar(List<UUID> treinamentosIds, UUID usuarioId) {

        List<TreinamentoBase> treinamentoBases = treinamentoBaseRespository.findAllById(treinamentosIds).stream().toList();
        List<Treinamento> treinamentos = new ArrayList<>();
        for (TreinamentoBase tb : treinamentoBases) {
            Treinamento treinamento = new Treinamento();
            treinamento.setTreinamento(tb.getTitulo());
            treinamento.setDescricao(tb.getDescricao());
            treinamento.setProtocolo(tb.getProtocolo().getDescricao());
            treinamento.setTenantId(usuarioId);
            treinamento.setImportId(tb.getTreinamentoBaseId());
            treinamento.setHabilidade(tb.getHabilidade());
            treinamento.setAtivo(true);

            List<Alvo> alvos = tb.getTreinos().stream().map(i -> Alvo.convert(i, treinamento)).toList();
            treinamento.setAlvos(alvos);
            treinamentos.add(treinamento);

        }
        treinamentoRespository.saveAll(treinamentos);

    }

    public List<Treinamento> findByTenantId(UUID usuarioId) {
        return treinamentoRespository.findByTenantId(usuarioId);
    }

    public void deleteTreinamento(UUID treinamentoId) {
        treinamentoRespository.deleteTreinamento(treinamentoId);
    }

    public Page<Treinamento> findAllAtivoIsTrue(PageRequest of) {
        return treinamentoRespository.findAllByAtivoIsTrue(of);
    }
}
