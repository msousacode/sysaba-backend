package br.com.sysaba.modules.relatorio;

import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.AtendimentoService;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.relatorio.client.RelatorioApiService;
import br.com.sysaba.modules.relatorio.dto.*;
import br.com.sysaba.modules.treinamento.Treinamento;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
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
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RelatorioService {

    private final AprendizService aprendizService;

    private final AtendimentoService atendimentoService;

    private final RelatorioApiService relatorioApiService;

    public RelatorioService(AprendizService aprendizService, AtendimentoService atendimentoService, RelatorioApiService relatorioApiService) {
        this.aprendizService = aprendizService;
        this.atendimentoService = atendimentoService;
        this.relatorioApiService = relatorioApiService;
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
}
