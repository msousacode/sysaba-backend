package br.com.sysaba.modules.avaliacoes.vbmapp;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VBMappBarreiraColetaDTO;
import br.com.sysaba.modules.avaliacoes.vbmapp.dto.VbMappBarreiraDTO;
import br.com.sysaba.modules.avaliacoes.vbmapp.repository.VBMappBarreiraRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.repository.VBMappColetaRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.repository.VBMappRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VBMappService {

    private final VBMappRepository vbMappRepository;

    private final VBMappColetaRepository vbMappColetaRepository;

    private final VBMappBarreiraRepository vbMappBarreiraRepository;

    private final AprendizService aprendizService;

    public VBMappService(VBMappRepository vbMappRepository, VBMappColetaRepository vbMappColetaRepository, VBMappBarreiraRepository vbMappBarreiraRepository, AprendizService aprendizService) {
        this.vbMappRepository = vbMappRepository;
        this.vbMappColetaRepository = vbMappColetaRepository;
        this.vbMappBarreiraRepository = vbMappBarreiraRepository;
        this.aprendizService = aprendizService;
    }

    public VbMappAvaliacao saveAvaliacao(VbMappAvaliacao vbMappAvaliacao) {
        return vbMappRepository.save(vbMappAvaliacao);
    }

    public VbMappAvaliacao findById(UUID vbmappId) {
        return vbMappRepository.findById(vbmappId).orElseThrow(() -> new RuntimeException("Não foi possível localizar a avaliação."));
    }

    public void saveColetaAvaliacao(List<VbMappColeta> vbMappColeta) {

        List<Integer> coletasIds = vbMappColeta.stream().map(i -> i.getColetaId()).toList();

        List<VbMappColeta> coletasRespondidas = vbMappColetaRepository.findAllColetasRespondidas(coletasIds);

        if (!coletasRespondidas.isEmpty()) {
            coletasRespondidas.forEach(i -> vbMappColetaRepository.deleteByVbmappColetaId(i.getVbmappColetaId()));
        }

        vbMappColetaRepository.saveAll(vbMappColeta);
    }

    public List<VbMappAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return vbMappRepository.findAllByAprendiz_aprendizId(aprendizId);
    }

    public List<VbMappColeta> findByColetasRespondidas(UUID vbmappUuid) {
        return vbMappColetaRepository.findByVbMapp_vbMappId(vbmappUuid);
    }

    public List<Double> findPontuacaoColetaAvaliacao(UUID vbmappUuid) {

        List<VbMappColeta> resultList = vbMappColetaRepository.findPontuacaoColetaAvaliacao(vbmappUuid);

        if (resultList.isEmpty()) {
            return List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        }

        int codigoNivelColeta = resultList.stream().findFirst().get().getNivelColeta();

        List<Double> coletaPontuacoes;
        if (codigoNivelColeta == 1) {
            coletaPontuacoes = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        } else if (codigoNivelColeta == 2) {
            coletaPontuacoes = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        } else {
            coletaPontuacoes = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
        }

        Map<Integer, List<VbMappColeta>> pessoasAgrupadas = resultList.stream().collect(Collectors.groupingBy(VbMappColeta::getTipo));

        pessoasAgrupadas.forEach((idx, coletas) -> {
            Double soma = 0.0;
            for (VbMappColeta num : coletas) {
                soma += num.getPontuacao() == -1 ? 0 : num.getPontuacao();
            }
            coletaPontuacoes.set((idx - 1), soma);
        });

        return coletaPontuacoes;
    }

    public Integer findColetasRespondidas(UUID vbmappId) {
        return vbMappColetaRepository.findColetasRespondidas(vbmappId);
    }

    public void salvarBarreiraColeta(VBMappBarreiraColetaDTO barreiraColetaDTO, UUID aprendizId) {

        Aprendiz aprendiz = aprendizService.findById(aprendizId);

        List<VbMappBarreira> vbMappBarreiras = new ArrayList<>();

        for (VbMappBarreiraDTO coleta : barreiraColetaDTO.getColetas()) {
            if (coleta.getVbMappBarreiraId() != null) {
                Optional<VbMappBarreira> result = vbMappBarreiraRepository.findById(coleta.getVbMappBarreiraId());

                if (result.isPresent()) {
                    result.get().setDescricao(coleta.getDescricao());
                    result.get().setResposta(coleta.getResposta());
                    result.get().setQuestao(coleta.getQuestao());
                    vbMappBarreiras.add(result.get());
                }
            } else {
                VbMappBarreira instance = VbMappBarreira.getInstance(coleta, aprendiz);
                vbMappBarreiras.add(instance);
            }
        }

        vbMappBarreiraRepository.saveAll(vbMappBarreiras);
    }
}
