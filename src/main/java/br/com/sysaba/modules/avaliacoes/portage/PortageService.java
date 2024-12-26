package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PortageService {

    private final PortageRepository portageRepository;
    private final PortageColetaRepository portageColetaRepository;

    public PortageService(PortageRepository portageRepository, PortageColetaRepository portageColetaRepository) {
        this.portageRepository = portageRepository;
        this.portageColetaRepository = portageColetaRepository;
    }

    public PortageAvaliacao saveAvaliacao(PortageAvaliacao portageAvaliacao) {
        return portageRepository.save(portageAvaliacao);
    }

    public void saveColetaAvaliacao(List<PortageColeta> portageColetaList) {

        List<Integer> coletasIds = portageColetaList.stream().map(i -> i.getColetaId()).toList();

        List<PortageColeta> coletasRespondidas = portageColetaRepository.findAllColetasRespondidas(coletasIds);

        if (!coletasRespondidas.isEmpty()) {
            coletasRespondidas.forEach(i -> portageColetaRepository.deleteByVbmappColetaId(i.getPortageColetaId()));
        }

        portageColetaRepository.saveAll(portageColetaList);
    }

    public PortageAvaliacao findById(UUID vbmappId) {
        return portageRepository.findById(vbmappId).orElseThrow(() -> new RuntimeException("Não foi possível localizar a avaliação."));
    }

    public List<PortageAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return portageRepository.findAllByAprendiz_aprendizId(aprendizId);
    }

    public List<PortageColeta> findByColetasRespondidas(UUID vbmappUuid) {
        return portageColetaRepository.findByPortage_portageId(vbmappUuid);
    }

    public List<Double> findDataSetPontuacaoPortage(UUID portageId) {

        List<PortageColeta> resultList = portageColetaRepository.findByPortage_portageId(portageId);

        if (resultList.isEmpty()) {
            return List.of(0.0, 0.0, 0.0, 0.0, 0.0);
        }

        List<Double> coletaPontuacoes;

        coletaPontuacoes = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0));

        Map<Integer, List<PortageColeta>> coletasAgrupadas = resultList.stream().collect(Collectors.groupingBy(i -> i.getTipo()));

        coletasAgrupadas.forEach((idx, coletas) -> {
            Double soma = 0.0;
            for (PortageColeta num : coletas) {
                soma += Double.parseDouble(num.getResposta()) == -1 ? 0 : Double.parseDouble(num.getResposta());
            }
            coletaPontuacoes.set((idx - 1), soma);
        });

        List<Double> coletaPontuacoesCalculadas;

        coletaPontuacoesCalculadas = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0));

        for (var coleta : coletasAgrupadas.entrySet()) {
            double soma = coleta.getValue().stream().map(i -> Double.parseDouble(i.getResposta())).mapToDouble(Double::doubleValue).sum();
            coletaPontuacoesCalculadas.set(coleta.getKey() - 1, calcularMediaIdadde(soma, coleta.getValue().get(0).getTipo()));
        }

        return coletaPontuacoesCalculadas;
    }

    private Double calcularMediaIdadde(Double soma, Integer tipo) {
        int pontosTotais;

        if (tipo == 1)
            pontosTotais = 28;
        else if (tipo == 2)
            pontosTotais = 10;
        else if (tipo == 3)
            pontosTotais = 14;
        else if (tipo == 4)
            pontosTotais = 13;
        else if (tipo == 5)
            pontosTotais = 45;
        else
            throw new RuntimeException("Necessário uma faixa etária para cálculo.");

        return ((soma * 12) / pontosTotais) / 12;
    }
}
