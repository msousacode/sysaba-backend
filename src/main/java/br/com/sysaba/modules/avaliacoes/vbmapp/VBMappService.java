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

    public void deleteAvaliacao(UUID avaliacaoId) {
        vbMappColetaRepository.deleteAllByVbMappId(avaliacaoId);
        vbMappRepository.deleteByVbmappId(avaliacaoId);
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
        return vbMappRepository.findAllByAprendiz_aprendizIdAndAtivoIsTrue(aprendizId);
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

    public void salvarBarreiraColeta(VBMappBarreiraColetaDTO barreiraColetaDTO, UUID aprendizId, UUID usuarioId) {

        Aprendiz aprendiz = aprendizService.findById(aprendizId);

        vbMappBarreiraRepository.deleteByAprendiz_aprendizId(aprendizId);

        List<VbMappBarreira> vbMappBarreiras = new ArrayList<>();

        for (VbMappBarreiraDTO coleta : barreiraColetaDTO.getColetas()) {
            VbMappBarreira instance = VbMappBarreira.getInstance(coleta, aprendiz, usuarioId);
            vbMappBarreiras.add(instance);
        }
        vbMappBarreiraRepository.saveAll(vbMappBarreiras);
    }

    public VBMappBarreiraColetaDTO findBarreirasRespondidas(UUID aprendizId) {
        VBMappBarreiraColetaDTO barreiraColetaDTO = new VBMappBarreiraColetaDTO();
        List<VbMappBarreiraDTO> coletas = vbMappBarreiraRepository.findByAprendiz_aprendizIdOrderByCodigoAsc(aprendizId).stream().map(i -> VbMappBarreiraDTO.of(i)).toList();
        barreiraColetaDTO.setColetas(coletas);
        return barreiraColetaDTO;
    }

    public List<Double> findPontuacaoBarreiraColeta(UUID aprendizId) {
        List<VbMappBarreira> resultList = vbMappBarreiraRepository.findPontuacaoBarreiraColeta(aprendizId);

        // Inicializando a lista com 24 posições preenchidas com 0.0
        List<Double> coletaPontuacoes = new ArrayList<>(Collections.nCopies(24, 0.0));

        for (int cont = 0; cont < resultList.size() && cont < 24; cont++) {
            if (resultList.get(cont) != null && resultList.get(cont).getResposta() != null) {
                int posicao = resultList.get(cont).getCodigo();

                // Verifica se a posição está dentro do limite de 0 a 23
                if (posicao >= 0 && posicao < 24) {
                    String resposta = resultList.get(cont).getResposta(); // Não precisa de stream aqui
                    coletaPontuacoes.set(posicao, Double.valueOf(resposta));
                }
            }
        }
        return coletaPontuacoes;
    }
}
