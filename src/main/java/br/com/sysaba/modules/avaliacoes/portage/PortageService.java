package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.avaliacoes.portage.enums.PortageAvaliacaoEnum;
import br.com.sysaba.modules.avaliacoes.portage.enums.PortageFaixaEnum;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortageService {

    private final PortageRepository portageRepository;
    private final PortageColetaRepository portageColetaRepository;

    public PortageService(PortageRepository portageRepository, PortageColetaRepository portageColetaRepository) {
        this.portageRepository = portageRepository;
        this.portageColetaRepository = portageColetaRepository;
    }

    public void deleteAvaliacao(UUID avaliacaoId) {
        portageRepository.deleteAllByPortageId(avaliacaoId);
        portageColetaRepository.deleteByPortageId(avaliacaoId);
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
        return portageRepository.findAllByAprendiz_aprendizIdAndAtivoIsTrue(aprendizId);
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
                soma += Double.parseDouble(num.getResposta()) == -1.0 ? 0 : Double.parseDouble(num.getResposta());
            }
            coletaPontuacoes.set((idx - 1), soma);
        });

        List<Double> coletaPontuacoesCalculadas;

        coletaPontuacoesCalculadas = new ArrayList<>(List.of(0.0, 0.0, 0.0, 0.0, 0.0));

        for (var coleta : coletasAgrupadas.entrySet()) {
            Map<String, Double> sumarizado = new HashMap<>();

            sumarizado.put("1", somarRespostasColetadas(coleta, PortageFaixaEnum.ZERO_A_UM_ANO));
            sumarizado.put("2", somarRespostasColetadas(coleta, PortageFaixaEnum.UM_A_DOIS_ANOS));
            sumarizado.put("3", somarRespostasColetadas(coleta, PortageFaixaEnum.DOIS_A_TRES_ANOS));
            sumarizado.put("4", somarRespostasColetadas(coleta, PortageFaixaEnum.TRES_A_QUATRO_ANOS));
            sumarizado.put("5", somarRespostasColetadas(coleta, PortageFaixaEnum.QUATRO_A_CINCO_ANOS));
            sumarizado.put("6", somarRespostasColetadas(coleta, PortageFaixaEnum.CINCO_A_SEIS_ANOS));

            Double total = 0.0;
            for (var soma : sumarizado.entrySet()) {
                if (soma.getValue() > 0.0) {
                    total += calcularMediaIdadde(soma.getValue(), coleta.getValue().get(0).getTipo(), soma.getKey());
                }
            }
            coletaPontuacoesCalculadas.set(coleta.getKey() - 1, total);
        }

        return coletaPontuacoesCalculadas;
    }

    private static double somarRespostasColetadas(Map.Entry<Integer, List<PortageColeta>> coleta, PortageFaixaEnum portageEnum) {
        List<PortageColeta> coletaSemNumerosNegativos = coleta.getValue().stream().filter(i -> Double.parseDouble(i.getResposta()) != -1.0).toList();
        return coletaSemNumerosNegativos.stream().filter(v -> portageEnum.getCod().equals(Integer.valueOf(v.getIdadeColeta()))).map(i -> Double.parseDouble(i.getResposta())).mapToDouble(Double::doubleValue).sum();
    }

    private Double calcularMediaIdadde(Double soma, Integer tipo, String idadeColeta) {

        int pontosTotais;

        PortageFaixaEnum portageIdade = PortageFaixaEnum.getByCod(Integer.valueOf(idadeColeta));

        PortageAvaliacaoEnum avalicao = PortageAvaliacaoEnum.getByCod(tipo);

        if (PortageAvaliacaoEnum.SOCIALIZACAO.equals(avalicao)) {
            if (PortageFaixaEnum.ZERO_A_UM_ANO.equals(portageIdade))
                pontosTotais = 28;
            else if (PortageFaixaEnum.UM_A_DOIS_ANOS.equals(portageIdade))
                pontosTotais = 15;
            else if (PortageFaixaEnum.DOIS_A_TRES_ANOS.equals(portageIdade))
                pontosTotais = 8;
            else if (PortageFaixaEnum.TRES_A_QUATRO_ANOS.equals(portageIdade))
                pontosTotais = 12;
            else if (PortageFaixaEnum.QUATRO_A_CINCO_ANOS.equals(portageIdade))
                pontosTotais = 9;
            else if (PortageFaixaEnum.CINCO_A_SEIS_ANOS.equals(portageIdade))
                pontosTotais = 11;
            else
                throw new RuntimeException("Não foi identificada a faixa de idade para o Portage.");
        } else if (PortageAvaliacaoEnum.LINGUAGEM.equals(avalicao))
            if (PortageFaixaEnum.ZERO_A_UM_ANO.equals(portageIdade))
                pontosTotais = 10;
            else if (PortageFaixaEnum.UM_A_DOIS_ANOS.equals(portageIdade))
                pontosTotais = 18;
            else if (PortageFaixaEnum.DOIS_A_TRES_ANOS.equals(portageIdade))
                pontosTotais = 30;
            else if (PortageFaixaEnum.TRES_A_QUATRO_ANOS.equals(portageIdade))
                pontosTotais = 12;
            else if (PortageFaixaEnum.QUATRO_A_CINCO_ANOS.equals(portageIdade))
                pontosTotais = 15;
            else if (PortageFaixaEnum.CINCO_A_SEIS_ANOS.equals(portageIdade))
                pontosTotais = 14;
            else
                throw new RuntimeException("Não foi identificada a faixa de idade para o Portage.");
        else if (PortageAvaliacaoEnum.COGNICAO.equals(avalicao))
            if (PortageFaixaEnum.ZERO_A_UM_ANO.equals(portageIdade))
                pontosTotais = 14;
            else if (PortageFaixaEnum.UM_A_DOIS_ANOS.equals(portageIdade))
                pontosTotais = 10;
            else if (PortageFaixaEnum.DOIS_A_TRES_ANOS.equals(portageIdade))
                pontosTotais = 16;
            else if (PortageFaixaEnum.TRES_A_QUATRO_ANOS.equals(portageIdade))
                pontosTotais = 24;
            else if (PortageFaixaEnum.QUATRO_A_CINCO_ANOS.equals(portageIdade))
                pontosTotais = 43;
            else if (PortageFaixaEnum.CINCO_A_SEIS_ANOS.equals(portageIdade))
                pontosTotais = 22;
            else
                throw new RuntimeException("Não foi identificada a faixa de idade para o Portage.");
        else if (PortageAvaliacaoEnum.AUTOCUIDADO.equals(avalicao))
            if (PortageFaixaEnum.ZERO_A_UM_ANO.equals(portageIdade))
                pontosTotais = 13;
            else if (PortageFaixaEnum.UM_A_DOIS_ANOS.equals(portageIdade))
                pontosTotais = 12;
            else if (PortageFaixaEnum.DOIS_A_TRES_ANOS.equals(portageIdade))
                pontosTotais = 27;
            else if (PortageFaixaEnum.TRES_A_QUATRO_ANOS.equals(portageIdade))
                pontosTotais = 15;
            else if (PortageFaixaEnum.QUATRO_A_CINCO_ANOS.equals(portageIdade))
                pontosTotais = 23;
            else if (PortageFaixaEnum.CINCO_A_SEIS_ANOS.equals(portageIdade))
                pontosTotais = 15;
            else
                throw new RuntimeException("Não foi identificada a faixa de idade para o Portage.");
        else if (PortageAvaliacaoEnum.MOTOR.equals(avalicao))
            if (PortageFaixaEnum.ZERO_A_UM_ANO.equals(portageIdade))
                pontosTotais = 45;
            else if (PortageFaixaEnum.UM_A_DOIS_ANOS.equals(portageIdade))
                pontosTotais = 18;
            else if (PortageFaixaEnum.DOIS_A_TRES_ANOS.equals(portageIdade))
                pontosTotais = 17;
            else if (PortageFaixaEnum.TRES_A_QUATRO_ANOS.equals(portageIdade))
                pontosTotais = 15;
            else if (PortageFaixaEnum.QUATRO_A_CINCO_ANOS.equals(portageIdade))
                pontosTotais = 16;
            else if (PortageFaixaEnum.CINCO_A_SEIS_ANOS.equals(portageIdade))
                pontosTotais = 29;
            else
                throw new RuntimeException("Não foi identificada a faixa de idade para o Portage.");
        else
            throw new RuntimeException("Necessário uma faixa etária para cálculo.");

        return ((soma * 12) / pontosTotais) / 12;
    }

    public Integer findColetasRespondidas(UUID portageId) {
        return portageColetaRepository.findColetasRespondidas(portageId);
    }
}
