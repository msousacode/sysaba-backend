package br.com.sysaba.modules.relatorio;

import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.AtendimentoService;
import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;
import br.com.sysaba.modules.avaliacoes.portage.PortageService;
import br.com.sysaba.modules.avaliacoes.portage.enums.PortageAvaliacaoEnum;
import br.com.sysaba.modules.avaliacoes.portage.enums.PortageFaixaEnum;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.relatorio.client.RelatorioApiService;
import br.com.sysaba.modules.relatorio.dto.*;
import br.com.sysaba.modules.relatorio.dto.portage.CabecalhoDTO;
import br.com.sysaba.modules.relatorio.dto.portage.DadosDTO;
import br.com.sysaba.modules.relatorio.dto.portage.PortageRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.portage.TabelaDTO;
import br.com.sysaba.modules.treinamento.Treinamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final AprendizService aprendizService;

    private final AtendimentoService atendimentoService;

    private final RelatorioApiService relatorioApiService;

    private final PortageService portageService;

    private final PortageColetaRepository portageColetaRepository;

    public RelatorioService(AprendizService aprendizService, AtendimentoService atendimentoService, RelatorioApiService relatorioApiService, PortageService portageService, PortageColetaRepository portageColetaRepository) {
        this.aprendizService = aprendizService;
        this.atendimentoService = atendimentoService;
        this.relatorioApiService = relatorioApiService;
        this.portageService = portageService;
        this.portageColetaRepository = portageColetaRepository;
    }

    public LinkDowloadResponseDTO gerarRelatorio(UUID aprendizId, String dataInicio, String dataFinal) {
        //TODO Dados dos profissionais envolvidos
        try {
            CabecarioDTO cabecalho = getCabecalhoDTO();
            AprendizDTO aprendiz = getAprendizDTO(aprendizId);
            List<TreinamentoDTO> treinamentos = getTreinamentos(aprendizId, dataInicio, dataFinal);

            if (treinamentos.isEmpty()) {
                return null;
            }

            RelatorioDTO relatorioDTO = new RelatorioDTO(cabecalho, List.of(), aprendiz, treinamentos);

            return relatorioApiService.postRelatorioTreinamentos(relatorioDTO);
        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        }
    }

    private CabecarioDTO getCabecalhoDTO() {
        // Obtém a data atual
        Date dataAtual = new Date();
        // Formata a data no padrão desejado
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(dataAtual);
        // Retorna a mensagem com a data formatada
        return new CabecarioDTO("Relatório gerado em: " + dataFormatada);
    }

    private AprendizDTO getAprendizDTO(UUID aprendizId) {
        Aprendiz aprendiz = aprendizService.findById(aprendizId);
        return new AprendizDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz()));
    }

    private List<TreinamentoDTO> getTreinamentos(UUID aprendizId, String dataInicio, String dataFinal) {

        //TODO fazer depois o limite por data.

        List<Atendimento> atendimentos = atendimentoService.findAllAtendimentosTreinamentosByAprendizId(aprendizId);

        List<TreinamentoDTO> treinamentos = new ArrayList<>();

        atendimentos.forEach(atendimento -> {

            atendimento.getTreinamentoAtendimentos().forEach(treinamento -> {
                TreinamentoDTO treinamentoDTO = new TreinamentoDTO();
                treinamentoDTO.setTreinamentoId(treinamento.getId());
                treinamentoDTO.setTitulo(treinamento.getTreinamento().getTreinamento());
                treinamentoDTO.setProtocolo(treinamento.getTreinamento().getProtocolo());
                treinamentoDTO.setDescricao(treinamento.getTreinamento().getDescricao());

                try {

                    if ("Protocolo ABC".equals(treinamento.getTreinamento().getProtocolo())) {
                        treinamentoDTO.setChartImage(createPieChart(treinamento.getTreinamento()));
                    } else {
                        List<String> imgChartList = createLineChart(treinamento.getTreinamento());
                        treinamentoDTO.setChartImageList(imgChartList);
                    }

                } catch (IOException e) {
                    throw new RuntimeException("Erro ao gerar o gráfico: ", e);
                }

                List<AlvoColetadoDTO> alvos = new ArrayList<>();

                treinamento.getTreinamento().getColetas().stream().filter(i -> i.getResposta() != null).forEach(coleta -> {
                    AlvoColetadoDTO alvoColetadoDTO = new AlvoColetadoDTO();
                    alvoColetadoDTO.setDataColeta(converterLocalDateTimeParaString(coleta.getDataColeta()));
                    alvoColetadoDTO.setDescricaoAlvo(coleta.getAlvo().getDescricaoAlvo());
                    alvoColetadoDTO.setResposta(coleta.getResposta());

                    List<AnotacaoDTO> anotacoes = new ArrayList<>();

                    coleta.getAnotacoes().stream().filter(anotacao -> anotacao.getImprimirRelatorio() != false).forEach(anotacao -> {
                        AnotacaoDTO anotacaoDTO = new AnotacaoDTO();
                        anotacaoDTO.setAnotacaoId(anotacao.getAnotacaoId());
                        anotacaoDTO.setData(converterLocalDateParaString(anotacao.getDataAnotacao()));
                        anotacaoDTO.setDescricao(anotacao.getAnotacao());
                        anotacoes.add(anotacaoDTO);
                    });

                    alvoColetadoDTO.setAnotacoes(anotacoes);

                    alvos.add(alvoColetadoDTO);
                });

                treinamentoDTO.setAlvosColetados(alvos);
                treinamentos.add(treinamentoDTO);
            });
        });
        return treinamentos;
    }

    private String calcularIdade(LocalDate dataNascimento) {
        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

        // Calcula a idade
        int idade = Period.between(dataNascimento, dataAtual).getYears();

        // Formata a data de nascimento
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataNascimento.format(formatter);

        // Constrói a mensagem
        return "Data de nascimento " + dataFormatada + " - " + idade + " anos";
    }

    private String converterLocalDateTimeParaString(LocalDateTime localDateTime) {
        if (localDateTime == null)
            return "Sem Data";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDateTime.format(formatter);
    }

    private String converterLocalDateParaString(LocalDate localDate) {
        if (localDate == null)
            return "Sem Data";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }

    public String createPieChart(Treinamento treinamento) throws IOException {

        long semAjuda = treinamento.getColetas().stream().filter(i -> "sem-ajuda".equals(i.getResposta())).count();
        long comAjuda = treinamento.getColetas().stream().filter(i -> "com-ajuda".equals(i.getResposta())).count();
        long naoFez = treinamento.getColetas().stream().filter(i -> "nao-fez".equals(i.getResposta())).count();

        // Criação do dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (semAjuda != 0)
            dataset.setValue("Fez", semAjuda);
        if (comAjuda != 0)
            dataset.setValue("Com Ajuda", comAjuda);
        if (naoFez != 0)
            dataset.setValue("Não Fez", naoFez);

        // Criação do gráfico
        JFreeChart pieChart = ChartFactory.createPieChart(
                null,  // Título
                dataset,             // Conjunto de dados
                true,                // Incluir legenda
                true,                // Usar tooltips
                false                // Não usar URLs
        );

        // Configurando as cores
        PiePlot plot = (PiePlot) pieChart.getPlot();
        if (semAjuda != 0)
            plot.setSectionPaint("Fez", new Color(58, 161, 119));
        if (comAjuda != 0)
            plot.setSectionPaint("Com Ajuda", new Color(255, 224, 102));
        if (naoFez != 0)
            plot.setSectionPaint("Não Fez", new Color(255, 102, 102));

        // Criação da imagem
        BufferedImage bufferedImage = pieChart.createBufferedImage(300, 300);

        // Convertendo a imagem em um formato Base64
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", outputStream);
        byte[] imageBytes = outputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        return "data:image/png;base64," + base64Image;
    }

    public List<String> createLineChart(Treinamento treinamento) throws IOException {
        List<String> imgList = new ArrayList<>();

        for (Alvo alvo : treinamento.getAlvos()) {
            // Para cada alvo, cria uma série
            XYSeries series = new XYSeries(alvo.getDescricaoAlvo());

            // Adiciona os dados na série
            for (Coleta coleta : treinamento.getColetas()) {
                if (coleta.getAlvo().getAlvoId() == alvo.getAlvoId()) {
                    if (coleta.getResposta() != null) {
                        series.add(coleta.getSemana(), Integer.parseInt(coleta.getResposta()));
                    }
                }
            }

            // Instancia o conjunto de dados e adiciona a série
            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(series);

            // Cria o gráfico
            JFreeChart lineChart = ChartFactory.createXYLineChart(
                    null,                          // Título
                    "Semana",                     // Rótulo do eixo X
                    "Qtde.",                      // Rótulo do eixo Y
                    dataset,                      // Conjunto de dados
                    PlotOrientation.VERTICAL,     // Orientação do gráfico
                    true,                         // Incluir legenda
                    true,                         // Usar tooltips
                    false                         // Não usar URLs
            );

            // Configurar o plot para usar XYSplineRenderer
            XYPlot plot = lineChart.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false); // Linhas, sem formas nos pontos
            renderer.setSeriesPaint(0, new Color(14, 51, 176)); // Cor para a série

            // Adiciona o Spline Renderer
            plot.setRenderer(renderer);

            // Formatar os eixos para mostrar apenas inteiros
            NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
            xAxis.setTickUnit(new NumberTickUnit(1)); // Definir unidade do tick x para 1

            NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
            yAxis.setTickUnit(new NumberTickUnit(1)); // Definir unidade do tick y para 1

            // Criação da imagem
            BufferedImage bufferedImage = lineChart.createBufferedImage(400, 400);

            // Convertendo a imagem em um formato Base64
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                ImageIO.write(bufferedImage, "png", outputStream);
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                imgList.add("data:image/png;base64," + base64Image);
            }
        }

        return imgList;
    }

    private TabelaDTO sumarizarPontuacaoAtingida(List<PortageColeta> resultList, PortageFaixaEnum faixaEtaria, PortageAvaliacaoEnum avaliacaoArea, TabelaDTO tabelaSocializacao) {
        List<PortageColeta> areaFilter = resultList.stream().filter(i -> avaliacaoArea.getCod().equals(i.getTipo())).toList();
        List<PortageColeta> idadeFilter = areaFilter.stream().filter(i -> faixaEtaria.getCod().equals(Integer.valueOf(i.getIdadeColeta()))).toList();

        tabelaSocializacao.setFaixaEtaria(faixaEtaria.getDescricao());
        tabelaSocializacao.setPontuacaoAtingida(idadeFilter.stream().map(i -> Double.valueOf(i.getResposta())).reduce(0.0, Double::sum));
        tabelaSocializacao.setTipo(avaliacaoArea.getCod());

        return tabelaSocializacao;
    }

    public LinkDowloadResponseDTO gerarRelatorioPorgate(UUID portageId) {

        List<PortageColeta> resultList = portageColetaRepository.findByPortage_portageId(portageId);

        if (resultList.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizado dados para gerar o relatório Portage.");
        }

        List<TabelaDTO> list = new ArrayList<>();

        List<TabelaDTO> socializacaoList = new ArrayList<>();
        List<TabelaDTO> cognicaoList = new ArrayList<>();
        List<TabelaDTO> linguagemList = new ArrayList<>();
        List<TabelaDTO> autocuidadoList = new ArrayList<>();
        List<TabelaDTO> motorList = new ArrayList<>();

        //resultList.stream().filter(i -> "1".equals(i.getIdadeColeta())).forEach(k -> {
        resultList.forEach(result -> {
            PortageFaixaEnum faixa = PortageFaixaEnum.getByCod(Integer.valueOf(result.getIdadeColeta()));

            TabelaDTO tabelaSocializacao = new TabelaDTO();
            tabelaSocializacao.setFaixaEtaria(faixa.getDescricao());
            TabelaDTO tb = sumarizarPontuacaoAtingida(resultList, faixa, PortageAvaliacaoEnum.getByCod(result.getTipo()), tabelaSocializacao);

            list.add(tb);
        });

        socializacaoList.addAll(list.stream().filter(i -> 1 == i.getTipo()).toList());
        cognicaoList.addAll(list.stream().filter(i -> 2 == i.getTipo()).toList());
        linguagemList.addAll(list.stream().filter(i -> 3 == i.getTipo()).toList());
        autocuidadoList.addAll(list.stream().filter(i -> 4 == i.getTipo()).toList());
        motorList.addAll(list.stream().filter(i -> 5 == i.getTipo()).toList());

        List<TabelaDTO> socializacaoListUnique = getUniqueList(socializacaoList);
        List<TabelaDTO> cognicaoListUnique = getUniqueList(cognicaoList);
        List<TabelaDTO> linguagemListUnique = getUniqueList(linguagemList);
        List<TabelaDTO> autocuidadoListUnique = getUniqueList(autocuidadoList);
        List<TabelaDTO> motorListUnique = getUniqueList(motorList);

        List<Double> pontuacoesCalculadas = portageService.findDataSetPontuacaoPortage(portageId);

        DadosDTO dadosDTOSocilizacao = new DadosDTO(
                PortageAvaliacaoEnum.SOCIALIZACAO.getDescricao(),
                PortageAvaliacaoEnum.SOCIALIZACAO.getCod(),
                socializacaoListUnique,
                pontuacoesCalculadas.get(0));

        DadosDTO dadosDTOCognicao = new DadosDTO(
                PortageAvaliacaoEnum.COGNICAO.getDescricao(),
                PortageAvaliacaoEnum.COGNICAO.getCod(),
                cognicaoListUnique,
                pontuacoesCalculadas.get(1));

        DadosDTO dadosDTOLinguagem = new DadosDTO(
                PortageAvaliacaoEnum.LINGUAGEM.getDescricao(),
                PortageAvaliacaoEnum.LINGUAGEM.getCod(),
                linguagemListUnique,
                pontuacoesCalculadas.get(2));

        DadosDTO dadosDTOAutocuidado = new DadosDTO(
                PortageAvaliacaoEnum.AUTOCUIDADO.getDescricao(),
                PortageAvaliacaoEnum.AUTOCUIDADO.getCod(),
                autocuidadoListUnique,
                pontuacoesCalculadas.get(3));

        DadosDTO dadosDTOMotor = new DadosDTO(
                PortageAvaliacaoEnum.MOTOR.getDescricao(),
                PortageAvaliacaoEnum.MOTOR.getCod(),
                motorListUnique,
                pontuacoesCalculadas.get(4));

        List<DadosDTO> dadosDTOS = List.of(dadosDTOSocilizacao, dadosDTOCognicao, dadosDTOLinguagem, dadosDTOAutocuidado, dadosDTOMotor);

        PortageRelatorioDTO portageRelatorioDTO = new PortageRelatorioDTO();
        portageRelatorioDTO.setTitulo("Relatório Portage");
        portageRelatorioDTO.setCabecalho(new CabecalhoDTO("zezinho", "12/12/2025"));
        portageRelatorioDTO.setDados(dadosDTOS);

        String chart;
        try {
            chart = getChartPortage(pontuacoesCalculadas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        portageRelatorioDTO.setChart(chart);

        return relatorioApiService.postRelatorioPortage(portageRelatorioDTO);
    }

    private static List<TabelaDTO> getUniqueList(List<TabelaDTO> list) {
        return list.stream()
                .collect(Collectors.toMap(
                        TabelaDTO::getFaixaEtaria, // Função de chave: nome
                        tabelaDTO -> tabelaDTO, // Função de valor: o próprio objeto
                        (existing, replacement) -> existing // Caso de colisão: mantém o existente
                ))
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    private String getChartPortage(List<Double> pontuacoesCalculadas) throws IOException {
        DefaultCategoryDataset datasetx = new DefaultCategoryDataset();

        // Adiciona as barras com valores variáveis
        datasetx.addValue(pontuacoesCalculadas.get(0), "1- Socialização", "1");
        datasetx.addValue(pontuacoesCalculadas.get(1), "2 - Cognição", "2");
        datasetx.addValue(pontuacoesCalculadas.get(2), "3 - Linguagem", "3");
        datasetx.addValue(pontuacoesCalculadas.get(3), "4 - Autocuidados", "4");
        datasetx.addValue(pontuacoesCalculadas.get(4), "5 - Motor", "5");

        // Adiciona a barra fixa de valor 6 ao lado de cada categoria
        for (int i = 1; i <= 5; i++) {
            datasetx.addValue(6, "Idade Atual", String.valueOf(i)); // "Valor Fixo" será a etiqueta para a barra fixa
        }

        DefaultCategoryDataset dataset = datasetx;

        // Cria o gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "",  // Título
                "",                  // Eixo X
                "Idade",                     // Eixo Y
                dataset,                       // Dataset
                PlotOrientation.VERTICAL,      // Orientação
                true,                          // Incluir legenda
                true,                          // Tooltips
                false                          // URLs
        );

        // Personaliza as cores das barras
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new BarRenderer();

        // Define as cores para cada barra
        renderer.setSeriesPaint(0, Color.RED);        // 1- Socialização
        renderer.setSeriesPaint(1, Color.GREEN);      // 2- Linguagem
        renderer.setSeriesPaint(2, Color.BLUE);       // 3- Cognição
        renderer.setSeriesPaint(3, Color.ORANGE);     // 4- Autocuidados
        renderer.setSeriesPaint(4, Color.MAGENTA);    // 5- Motor
        renderer.setSeriesPaint(5, Color.GRAY); // Marrom para a barra fixa (RGB)
        renderer.setMaximumBarWidth(2);
        plot.setRenderer(renderer);

        // Criação da imagem
        BufferedImage bufferedImage = chart.createBufferedImage(600, 600); // parâmetros que aumentam a qualidade da imagem.

        // Convertendo a imagem em um formato Base64
        String imgChart;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            imgChart = "data:image/png;base64," + base64Image;
        }

        return imgChart;
    }
}
