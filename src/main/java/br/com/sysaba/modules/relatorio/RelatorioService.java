package br.com.sysaba.modules.relatorio;

import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.AtendimentoService;
import br.com.sysaba.modules.avaliacoes.ablls.AbllsColeta;
import br.com.sysaba.modules.avaliacoes.ablls.AbllsColetaRepository;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsHabilidadeEnum;
import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;
import br.com.sysaba.modules.avaliacoes.portage.PortageService;
import br.com.sysaba.modules.avaliacoes.portage.enums.PortageAvaliacaoEnum;
import br.com.sysaba.modules.avaliacoes.portage.enums.PortageFaixaEnum;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappBarreira;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappColeta;
import br.com.sysaba.modules.avaliacoes.vbmapp.enums.VBMappNivelDoisEnum;
import br.com.sysaba.modules.avaliacoes.vbmapp.enums.VBMappNivelTresEnum;
import br.com.sysaba.modules.avaliacoes.vbmapp.enums.VBMappNivelUmEnum;
import br.com.sysaba.modules.avaliacoes.vbmapp.repository.VBMappBarreiraRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.repository.VBMappColetaRepository;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.relatorio.client.RelatorioApiService;
import br.com.sysaba.modules.relatorio.dto.*;
import br.com.sysaba.modules.relatorio.dto.barreiras.TabelaBarreiraDTO;
import br.com.sysaba.modules.relatorio.dto.barreiras.VBMappBarreiraRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.pei.PEIAbllsDadoDTO;
import br.com.sysaba.modules.relatorio.dto.pei.PEIDadoDTO;
import br.com.sysaba.modules.relatorio.dto.pei.PEIObjetivoDTO;
import br.com.sysaba.modules.relatorio.dto.pei.PEIRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.portage.CabecalhoDTO;
import br.com.sysaba.modules.relatorio.dto.portage.DadosDTO;
import br.com.sysaba.modules.relatorio.dto.portage.PortageRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.portage.TabelaDTO;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.base.HabilidadeBaseEnum;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
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

    private final VBMappColetaRepository vbMappColetaRepository;

    private final VBMappBarreiraRepository vbMappBarreiraRepository;

    private final AbllsColetaRepository abllsColetaRepository;

    private final UsuarioService usuarioService;

    public RelatorioService(AprendizService aprendizService, AtendimentoService atendimentoService, RelatorioApiService relatorioApiService, PortageService portageService, PortageColetaRepository portageColetaRepository, VBMappColetaRepository vbMappColetaRepository, VBMappBarreiraRepository vbMappBarreiraRepository, AbllsColetaRepository abllsColetaRepository, UsuarioService usuarioService) {
        this.aprendizService = aprendizService;
        this.atendimentoService = atendimentoService;
        this.relatorioApiService = relatorioApiService;
        this.portageService = portageService;
        this.portageColetaRepository = portageColetaRepository;
        this.vbMappColetaRepository = vbMappColetaRepository;
        this.vbMappBarreiraRepository = vbMappBarreiraRepository;
        this.abllsColetaRepository = abllsColetaRepository;
        this.usuarioService = usuarioService;
    }

    public LinkDowloadResponseDTO gerarRelatorio(UUID aprendizId, String dataInicio, String dataFinal) {
        //TODO Dados dos profissionais envolvidos
        try {
            AprendizDTO aprendiz = getAprendizDTO(aprendizId);
            List<TreinamentoDTO> treinamentos = getTreinamentos(aprendizId, dataInicio, dataFinal);

            if (treinamentos.isEmpty()) {
                return null;
            }

            CabecalhoDTO cabecalho = new CabecalhoDTO(aprendiz.getNome(), aprendiz.getNascimento());
            RelatorioDTO relatorioDTO = new RelatorioDTO("Relatório Geral - Intervenção", cabecalho, List.of(), aprendiz, treinamentos);


            return relatorioApiService.postRelatorioTreinamentos(relatorioDTO);
        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        }
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

                /** TODO o gráfico foi desbilitado até encontrar uma maneira melhor de gerar esses gráficos
                 * pois hoje os gráficos estão muito feios e estão quebrabdo o layout da impressão.
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
                 **/

                treinamentoDTO.setChartImageList(List.of());

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
        return dataFormatada + " - " + idade + " anos";
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

        Aprendiz aprendiz = aprendizService.findById(resultList.get(0).getAprendiz().getAprendizId());

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

        UUID profissionalColetaId = resultList.stream().findFirst().get().getCriadoPor();
        Usuario usuario = usuarioService.findById(profissionalColetaId);

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(usuario.getFullName());
        profissionalDTO.setRegistro(usuario.getDocumento() == null ? "" : usuario.getDocumento());

        PortageRelatorioDTO portageRelatorioDTO = new PortageRelatorioDTO();
        portageRelatorioDTO.setTitulo("Relatório Portage");
        portageRelatorioDTO.setCabecalho(new CabecalhoDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz())));
        portageRelatorioDTO.setDados(dadosDTOS);
        portageRelatorioDTO.setProfissionais(List.of(profissionalDTO));

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
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Adiciona as barras com valores variáveis
        dataset.setValue(pontuacoesCalculadas.get(0), "1- Socialização", "Soc");
        dataset.setValue(6, "a", "Soc");

        dataset.setValue(pontuacoesCalculadas.get(1), "2 - Cognição", "Cog");
        dataset.setValue(6, "b", "Cog");

        dataset.setValue(pontuacoesCalculadas.get(2), "3 - Linguagem", "Ling");
        dataset.setValue(6, "c", "Ling");

        dataset.setValue(pontuacoesCalculadas.get(3), "4 - Autocuidados", "AutoC");
        dataset.setValue(6, "d", "AutoC");

        dataset.setValue(pontuacoesCalculadas.get(4), "5 - Motor", "Mor");
        dataset.setValue(6, "e", "Mor");

        // Cria o gráfico
        JFreeChart chart = ChartFactory.createBarChart(
                "",                      // Título
                "",                      // Eixo X - permanece vazio para agrupar
                "Idade",                // Eixo Y
                dataset,                // Dataset
                PlotOrientation.VERTICAL, // Orientação
                false,                   // Incluir legenda
                true,                   // Tooltips
                false                   // URLs
        );

        // Personalizando o gráfico para deixar as barras juntas
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Define as cores para cada barra
        renderer.setSeriesPaint(0, Color.RED);        // 1- Socialização
        renderer.setSeriesPaint(1, Color.ORANGE);

        renderer.setSeriesPaint(2, Color.GREEN);      // 2- Linguagem
        renderer.setSeriesPaint(3, Color.ORANGE);

        renderer.setSeriesPaint(4, Color.BLUE);       // 3- Cognição
        renderer.setSeriesPaint(5, Color.ORANGE);

        renderer.setSeriesPaint(6, Color.CYAN);     // 4- Autocuidados
        renderer.setSeriesPaint(7, Color.ORANGE);

        renderer.setSeriesPaint(8, Color.MAGENTA);    // 5- Motor
        renderer.setSeriesPaint(9, Color.ORANGE);       // Idade Atual

        // Criação da imagem
        BufferedImage bufferedImage = chart.createBufferedImage(600, 600);

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

    public LinkDowloadResponseDTO gerarRelatorioPorgatePEI(UUID portageId) {
        List<PortageColeta> resultList = portageColetaRepository.findByPortage_portageId(portageId);

        if (resultList.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizado dados para gerar o relatório PEI Portage.");
        }

        Aprendiz aprendiz = resultList.stream().findFirst().get().getAprendiz();

        UUID profissionalColetaId = resultList.stream().findFirst().get().getCriadoPor();
        Usuario usuario = usuarioService.findById(profissionalColetaId);

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(usuario.getFullName());
        profissionalDTO.setRegistro(usuario.getDocumento() == null ? "" : usuario.getDocumento());

        PEIRelatorioDTO peiDTO = new PEIRelatorioDTO();
        peiDTO.setTitulo("PEI - Plano Educacional Individualizado - Portage");
        peiDTO.setCabecario(new CabecalhoDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz())));
        peiDTO.setProfissionais(List.of(profissionalDTO));

        List<PEIDadoDTO> dados = new ArrayList<>();

        PEIDadoDTO socializacaoPEIDadoDTO = getPeiDadoDTO(resultList, "Socialização", PortageAvaliacaoEnum.SOCIALIZACAO.getCod());
        PEIDadoDTO cognicaoPEIDadoDTO = getPeiDadoDTO(resultList, "Cognição", PortageAvaliacaoEnum.COGNICAO.getCod());
        PEIDadoDTO linguagemPEIDadoDTO = getPeiDadoDTO(resultList, "Linguagem", PortageAvaliacaoEnum.LINGUAGEM.getCod());
        PEIDadoDTO autocuidadoPEIDadoDTO = getPeiDadoDTO(resultList, "Autocuidado", PortageAvaliacaoEnum.AUTOCUIDADO.getCod());
        PEIDadoDTO motorPEIDadoDTO = getPeiDadoDTO(resultList, "Motor", PortageAvaliacaoEnum.MOTOR.getCod());

        dados.add(socializacaoPEIDadoDTO);
        dados.add(cognicaoPEIDadoDTO);
        dados.add(linguagemPEIDadoDTO);
        dados.add(autocuidadoPEIDadoDTO);
        dados.add(motorPEIDadoDTO);

        peiDTO.setDados(dados);

        return relatorioApiService.postPEIRelatorioPortage(peiDTO);
    }

    private PEIDadoDTO getPeiDadoDTO(List<PortageColeta> resultList, String titulo, Integer tipo) {
        PEIDadoDTO socializacaoPEIDadoDTO = new PEIDadoDTO();
        socializacaoPEIDadoDTO.setTitulo(titulo);

        List<PEIObjetivoDTO> objetivosZero = resultList.stream().filter(c -> c.getTipo() == tipo).filter(i -> Double.valueOf(i.getResposta()) == 0).map(k -> new PEIObjetivoDTO(String.valueOf(k.getCodigo()), k.getDescricao(), k.getTipo())).toList();
        List<PEIObjetivoDTO> objetivosMeio = resultList.stream().filter(c -> c.getTipo() == tipo).filter(i -> Double.valueOf(i.getResposta()) == 0.5).map(k -> new PEIObjetivoDTO(String.valueOf(k.getCodigo()), k.getDescricao(), k.getTipo())).toList();

        socializacaoPEIDadoDTO.setObjetivosZero(objetivosZero);
        socializacaoPEIDadoDTO.setObjetivosMeio(objetivosMeio);
        return socializacaoPEIDadoDTO;
    }

    public LinkDowloadResponseDTO getRelatorioVbMappPEI(UUID aprendizId) {
        List<VbMappColeta> resultList = vbMappColetaRepository.findByAprendiz_aprendizId(aprendizId);

        UUID profissionalColetaId = resultList.stream().findFirst().get().getCriadoPor();
        Usuario usuario = usuarioService.findById(profissionalColetaId);

        if (resultList.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizado dados para gerar o relatório PEI VBMAPP.");
        }

        List<VbMappColeta> resultListNivel1 = resultList.stream().filter(i -> i.getNivelColeta() == 1).toList();
        List<VbMappColeta> resultListNivel2 = resultList.stream().filter(i -> i.getNivelColeta() == 2).toList();
        List<VbMappColeta> resultListNivel3 = resultList.stream().filter(i -> i.getNivelColeta() == 3).toList();

        List<List<VbMappColeta>> todosResultListByNivel = new ArrayList<>();
        todosResultListByNivel.add(resultListNivel1);
        todosResultListByNivel.add(resultListNivel2);
        todosResultListByNivel.add(resultListNivel3);

        Aprendiz aprendiz = resultList.stream().findFirst().get().getAprendiz();

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(usuario.getFullName());
        profissionalDTO.setRegistro(usuario.getDocumento() == null ? "" : usuario.getDocumento());

        PEIRelatorioDTO peiDTO = new PEIRelatorioDTO();
        peiDTO.setTitulo("PEI - Plano Educacional Individualizado - VBMAPP");
        peiDTO.setCabecario(new CabecalhoDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz())));
        peiDTO.setProfissionais(List.of(profissionalDTO));

        List<List<PEIDadoDTO>> dados = new ArrayList<>();

        for (List<VbMappColeta> list : todosResultListByNivel) {
            dados.add(getPeiDadoDTO(list));
        }

        peiDTO.getDadosNiveisVbMapp().addAll(dados);

        return relatorioApiService.postPEIRelatorioVBMAPP(peiDTO);
    }

    private List<PEIDadoDTO> getPeiDadoDTO(List<VbMappColeta> list) {
        List<PEIDadoDTO> dados = new ArrayList<>();

        if (list.isEmpty())
            return dados;

        if (list.stream().findFirst().get().getNivelColeta() == 1) {//Nível 1

            for (VbMappColeta coleta : list) {
                PEIDadoDTO peiDadoDTO = new PEIDadoDTO();
                peiDadoDTO.setNivel(list.stream().findFirst().get().getNivelColeta());
                peiDadoDTO.setTitulo(VBMappNivelUmEnum.getByCod(coleta.getTipo()).getDescricao());

                List<PEIObjetivoDTO> objetivosZero = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();
                List<PEIObjetivoDTO> objetivosMeio = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0.5).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();

                peiDadoDTO.setObjetivosZero(objetivosZero);
                peiDadoDTO.setObjetivosMeio(objetivosMeio);
                dados.add(peiDadoDTO);
            }

        }

        if (list.stream().findFirst().get().getNivelColeta() == 2) {//Nível 2
            for (VbMappColeta coleta : list) {
                PEIDadoDTO peiDadoDTO = new PEIDadoDTO();
                peiDadoDTO.setNivel(list.stream().findFirst().get().getNivelColeta());
                peiDadoDTO.setTitulo(VBMappNivelDoisEnum.getByCod(coleta.getTipo()).getDescricao());

                List<PEIObjetivoDTO> objetivosZero = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();
                List<PEIObjetivoDTO> objetivosMeio = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0.5).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();

                peiDadoDTO.setObjetivosZero(objetivosZero);
                peiDadoDTO.setObjetivosMeio(objetivosMeio);
                dados.add(peiDadoDTO);
            }
        }

        if (list.stream().findFirst().get().getNivelColeta() == 3) {//Nível 3
            for (VbMappColeta coleta : list) {
                PEIDadoDTO peiDadoDTO = new PEIDadoDTO();
                peiDadoDTO.setNivel(list.stream().findFirst().get().getNivelColeta());
                peiDadoDTO.setTitulo(VBMappNivelTresEnum.getByCod(coleta.getTipo()).getDescricao());

                List<PEIObjetivoDTO> objetivosZero = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();
                List<PEIObjetivoDTO> objetivosMeio = list.stream().filter(c -> c.getTipo() == coleta.getTipo()).filter(i -> Double.valueOf(i.getPontuacao()) == 0.5).map(k -> new PEIObjetivoDTO(k.getCodigo(), k.getDescricao(), k.getTipo())).toList();

                peiDadoDTO.setObjetivosZero(objetivosZero);
                peiDadoDTO.setObjetivosMeio(objetivosMeio);
                dados.add(peiDadoDTO);
            }
        }

        List<PEIDadoDTO> distinctList = new ArrayList<>();
        Set<String> set = new HashSet<>();

        for (PEIDadoDTO item : dados) {
            if (set.add(item.getTitulo())) { // add retorna false se o elemento já existir
                distinctList.add(item);
            }
        }

        return distinctList;
    }

    public LinkDowloadResponseDTO getRelatorioVbMappBarreiras(UUID aprendizId) {

        List<VbMappBarreira> barreiras = vbMappBarreiraRepository.findByAprendiz_aprendizIdOrderByCodigoAsc(aprendizId);

        if (barreiras.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizados registros para gerar o relatório de barreiras para o aprendiz: " + aprendizId);
        }

        VBMappBarreiraRelatorioDTO vbMappBarreiraRelatorioDTO = new VBMappBarreiraRelatorioDTO();

        String charImg;
        try {
            charImg = getVbMappBarreirasChartImg(barreiras);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<TabelaBarreiraDTO> tabelaBarreiraDTOS = new ArrayList<>();

        for (VbMappBarreira barreira : barreiras) {
            tabelaBarreiraDTOS.add(TabelaBarreiraDTO.convert(barreira));
        }

        Aprendiz aprendiz = barreiras.stream().findFirst().get().getAprendiz();

        UUID profissionalColetaId = barreiras.stream().findFirst().get().getCriadoPor();
        Usuario usuario = usuarioService.findById(profissionalColetaId);

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(usuario.getFullName());
        profissionalDTO.setRegistro(usuario.getDocumento() == null ? "" : usuario.getDocumento());

        vbMappBarreiraRelatorioDTO.setTitulo("Relatório de Barreiras - VB-MAPP");
        vbMappBarreiraRelatorioDTO.setCabecalhoDTO(new CabecalhoDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz())));
        vbMappBarreiraRelatorioDTO.setChartImg(charImg);
        vbMappBarreiraRelatorioDTO.setTabela(tabelaBarreiraDTOS);
        vbMappBarreiraRelatorioDTO.setProfissionais(List.of(profissionalDTO));

        return relatorioApiService.postVBBarreiraRelatorioVBMAPP(vbMappBarreiraRelatorioDTO);
    }

    private String getVbMappBarreirasChartImg(List<VbMappBarreira> barreiras) throws IOException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (VbMappBarreira vb : barreiras) {
            Integer codY = vb.getCodigo() + 1;
            if (codY > 24) {
                break;
            }
            dataset.setValue(Integer.valueOf(vb.getResposta()), codY.toString(), codY.toString());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "",    // Título
                "",                   // Eixo X
                "Pontos",                      // Eixo Y
                dataset,                       // Dataset
                PlotOrientation.HORIZONTAL,     // Orientação
                false,                         // Incluir legenda
                true,                          // Tooltips
                false                          // URLs
        );

        // Personalizando o gráfico
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // Definindo cores para cada barra
        for (int i = 0; i < barreiras.size(); i++) {
            renderer.setSeriesPaint(i, new Color((int) (Math.random() * 0x1000000))); // Cores aleatórias
        }

        renderer.setMaximumBarWidth(0.1); // Ajustar a largura máxima da barra
        renderer.setItemMargin(0.1); // Ajuste de margem entre as barras

        // Adicionando anotações como valores
        plot.getRenderer().setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        plot.getRenderer().setDefaultItemLabelsVisible(true);

        // Adiciona linhas de grade
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);

        BufferedImage bufferedImage = chart.createBufferedImage(500, 700);

        String imgChart;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            imgChart = "data:image/png;base64," + base64Image;
        }

        return imgChart;
    }

    public LinkDowloadResponseDTO getRelatorioAbllsPEI(UUID abllsId) {

        List<AbllsColeta> resultList = abllsColetaRepository.findByAblls_abllsId(abllsId);

        UUID profissionalColetaId = resultList.stream().findFirst().get().getCriadoPor();
        Usuario usuario = usuarioService.findById(profissionalColetaId);

        if (resultList.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foi localizado dados para gerar o relatório PEI ABLLS.");
        }

        Aprendiz aprendiz = resultList.stream().findFirst().get().getAprendiz();

        ProfissionalDTO profissionalDTO = new ProfissionalDTO();
        profissionalDTO.setNome(usuario.getFullName());
        profissionalDTO.setRegistro(usuario.getDocumento() == null ? "" : usuario.getDocumento());

        PEIRelatorioDTO peiDTO = new PEIRelatorioDTO();
        peiDTO.setTitulo("PEI - Plano Educacional Individualizado - ABLLS-R");
        peiDTO.setCabecario(new CabecalhoDTO(aprendiz.getNomeAprendiz(), calcularIdade(aprendiz.getNascAprendiz())));
        peiDTO.setProfissionais(List.of(profissionalDTO));

        List<PEIAbllsDadoDTO> dados = new ArrayList<>();

        List<List<AbllsColeta>> listaGeral = new ArrayList<>();

        for (AbllsHabilidadeEnum habilidadeEnum : AbllsHabilidadeEnum.getLookup()) {
            listaGeral.add(resultList.stream().filter(i -> habilidadeEnum.equals(i.getHabilidade())).toList());
        }

        for (List<AbllsColeta> list : listaGeral) {
            if (!list.isEmpty()) {
                dados.add(getAbllsDadoDTO(list));
            }
        }

        peiDTO.setDadosAblls(dados);

        return relatorioApiService.postPEIRelatorioVBMAPP(peiDTO);
    }

    private PEIAbllsDadoDTO getAbllsDadoDTO(List<AbllsColeta> list) {
        PEIAbllsDadoDTO peiAbllsDadoDTO = new PEIAbllsDadoDTO();
        peiAbllsDadoDTO.setHabilidade(list.stream().findFirst().get().getHabilidade().name());
        peiAbllsDadoDTO.setTitulo("Teste");

        List<AbllsColeta> priorioritarioAteDoisPontosList = list.stream().filter(i -> i.getPontuacaoMax() == 2 && i.getResposta() == 1).toList();
        List<AbllsColeta> demaisAteDoisPontosList = list.stream().filter(i -> i.getPontuacaoMax() == 2 && i.getResposta() == 0).toList();

        List<AbllsColeta> priorioritarioAteQuatroPontosList = list.stream().filter(i -> i.getPontuacaoMax() == 4 && (i.getResposta() == 2 || i.getResposta() == 3)).toList();
        List<AbllsColeta> demaisAteQuatroPontosList = list.stream().filter(i -> i.getPontuacaoMax() == 4 && (i.getResposta() == 0 || i.getResposta() == 1)).toList();

        //Até dois
        if (!priorioritarioAteDoisPontosList.isEmpty()) {
            List<PEIObjetivoDTO> objsPrioritarios = priorioritarioAteDoisPontosList.stream().map(PEIObjetivoDTO::of).toList();
            peiAbllsDadoDTO.setObjetivosPrioritariosAteDoisPontos(objsPrioritarios);
        }

        if (!demaisAteDoisPontosList.isEmpty()) {
            List<PEIObjetivoDTO> demaisObjetivos = demaisAteDoisPontosList.stream().map(PEIObjetivoDTO::of).toList();
            peiAbllsDadoDTO.setObjetivosZeroPontosAteDoisPontos(demaisObjetivos);
        }

        //Até 4 pontos
        if (!priorioritarioAteQuatroPontosList.isEmpty()) {
            List<PEIObjetivoDTO> objsPrioritarios = priorioritarioAteQuatroPontosList.stream().map(PEIObjetivoDTO::of).toList();
            peiAbllsDadoDTO.setObjetivosPrioritariosAteQuatroPontos(objsPrioritarios);
        }

        if (!demaisAteQuatroPontosList.isEmpty()) {
            List<PEIObjetivoDTO> demaisObjetivos = demaisAteQuatroPontosList.stream().map(PEIObjetivoDTO::of).toList();
            peiAbllsDadoDTO.setObjetivosZeroAteUmPntoAteQuatroPontos(demaisObjetivos);
        }

        return peiAbllsDadoDTO;
    }
}
