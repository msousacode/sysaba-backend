package br.com.sysaba.modules.faturamento;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizController;
import br.com.sysaba.modules.aprendiz.AprendizRespository;
import br.com.sysaba.modules.cargo.Cargo;
import br.com.sysaba.modules.cargo.CargoRespository;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/faturamento")
public class FaturamentoController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final FaturamentoRepository faturamentoRepository;

    private final CargoRespository cargoRespository;

    private final AprendizRespository aprendizRespository;

    private final UsuarioService usuarioService;

    public FaturamentoController(FaturamentoRepository faturamentoRepository, CargoRespository cargoRespository, AprendizRespository aprendizRespository, UsuarioService usuarioService) {
        this.faturamentoRepository = faturamentoRepository;
        this.cargoRespository = cargoRespository;
        this.aprendizRespository = aprendizRespository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody FaturamentoDTO faturamentoDTO) {
        try {
            FaturamentoGeral faturamento = MapperUtil.converte(faturamentoDTO, FaturamentoGeral.class);

            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Usuario usuario = usuarioService.getByEmail(email);
            UUID usuarioId = usuario.getUsuarioId();

            if (usuario.getCargo() == null)
                return ResponseEntity.internalServerError().body("Usuário esta sem cargo.");

            Cargo cargo = cargoRespository.findById(usuario.getCargo().getCargoId()).get();

            Aprendiz aprendiz = aprendizRespository.findById(faturamentoDTO.getAprendizId()).get();

            faturamento.setProfissionalId(usuarioId);
            faturamento.setNomeProfissional(usuario.getFullName());
            faturamento.setCargoProfissional(cargo.getDescricao());
            faturamento.setPreco(cargo.getPreco());

            faturamento.setAprendizId(aprendiz.getAprendizId());
            faturamento.setNomeAprendiz(aprendiz.getNomeAprendiz());

            if (faturamentoDTO.getPresente() == null)
                throw new RuntimeException("É obrigatório informar se o aprendiz está ou não presente na sessão.");

            faturamento.setPresente(faturamentoDTO.getPresente());

            if (!faturamentoDTO.getPresente()) {//Se false significa que é uma ausẽncia.
                if (faturamentoDTO.getAusenciaJustificada() != null) {
                    faturamento.setAusenciaJustificada(faturamentoDTO.getAusenciaJustificada());
                    if (StringUtils.hasText(faturamento.getDescricaoJustificativa())) {
                        faturamento.setDescricaoJustificativa(faturamento.getDescricaoJustificativa());
                    } else {
                        throw new RuntimeException("Em caso de ausência é necessário inseir uma justificativa.");
                    }
                } else {
                    throw new RuntimeException("É necessário inserir se a ausência é ou não justificada.");
                }
            }

            faturamento.setSituacao(PagamentoEnum.PENDENTE);
            faturamento.setCriadoPor(usuarioId);

            faturamento.setDia(LocalDate.now().getDayOfMonth());
            faturamento.setMes(LocalDate.now().getMonthValue());
            faturamento.setAno(LocalDate.now().getYear());

            faturamento.setTenantId(usuario.getTenantId());

            faturamentoRepository.save(faturamento);

            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FaturamentoDTO>> getAll() {
        List<Object[]> faturamentos = faturamentoRepository.findSomatorioPrecosGeral();

        List<FaturamentoDTO> faturamentoDTOS = faturamentos.stream()
                .map(result -> FaturamentoDTO.of(result))
                .collect(Collectors.toList());

        for (FaturamentoDTO dto : faturamentoDTOS) {
            Integer totalSessoesRealizadas = faturamentoRepository.findCountSessoesRealizadasPorAprendizId(dto.getAprendizId(), Integer.valueOf(dto.getMes()), Integer.valueOf(dto.getAno()));
            Integer totalAusenciasJustificadas = faturamentoRepository.findCountAusenciasJustificadasPorAprendizId(dto.getAprendizId(), Integer.valueOf(dto.getMes()), Integer.valueOf(dto.getAno()));
            Integer totalAuenciasNaoJustificadas = faturamentoRepository.findCountAuenciasNaoJustificadasPorAprendizId(dto.getAprendizId(), Integer.valueOf(dto.getMes()), Integer.valueOf(dto.getAno()));

            dto.setSessoesTotal(totalSessoesRealizadas);
            dto.setAusenciasJustificadasTotal(totalAusenciasJustificadas);
            dto.setAusenciasNaoJustificadasTotal(totalAuenciasNaoJustificadas);
            dto.setValorTotal(dto.getValorTotal());
        }

        return ResponseEntity.status(HttpStatus.OK).body(faturamentoDTOS);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FaturamentoBuscarDTO>> buscar(
            @RequestParam(required = false) UUID aprendizId
    ) {

        Specification<FaturamentoGeral> spec = Specification.where(
                (root, query, cb) -> cb.equal(root.get("ativo"), true)
        );

        if (aprendizId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("aprendizId"), aprendizId));
        }

        List<FaturamentoGeral> faturamentoList = faturamentoRepository.findAll(spec);

        List<FaturamentoBuscarDTO> response = faturamentoList.stream()
                .map(i -> FaturamentoBuscarDTO.of(i, 10, 2, 3, "R$ 5.000,00"))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dash")
    public FaturamentoSumarioDTO findInfoDash() {

        Integer mes = LocalDate.now().getMonthValue();
        Integer ano = LocalDate.now().getYear();

        List<Object[]> totalValorPrevisto = faturamentoRepository.findSomatorioTotalValorPrevisto(mes, ano);
        List<Object[]> totalPago = faturamentoRepository.findSomatorioTotalPago(mes, ano);
        Integer totalSessoesRealizadas = faturamentoRepository.findSomatorioSessoesRealizadas(mes, ano);
        Integer totalAusenciasJustificadas = faturamentoRepository.findSomatorioAusenciasJustificadas(mes, ano);
        Integer totalAusenciasNaoJustificadas = faturamentoRepository.findSomatorioAusenciasNaoJustificadas(mes, ano);
        Integer totalAprendizes = aprendizRespository.findTotalAprendizes();

        Double valorAReceber = 0.0;

        if(!totalValorPrevisto.isEmpty()) {
            for(int i =0 ; i < totalValorPrevisto.size(); i++) {
                valorAReceber +=  (Double) totalValorPrevisto.get(i)[0];
            }
        }

        Double valorPago = !totalPago.isEmpty() ? (Double) totalPago.get(0)[0] : 0;

        Locale localeBR = new Locale("pt", "BR");
        NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(localeBR);

        BigDecimal valorTotalAReceber = new BigDecimal(valorAReceber).subtract(new BigDecimal(valorPago));

        FaturamentoSumarioDTO dto = new FaturamentoSumarioDTO();

        dto.setFaturamentoTotalMes(formatadorMoeda.format(valorAReceber));
        dto.setValorTotalPagos(formatadorMoeda.format(valorPago));
        dto.setValorTotalPendentes(formatadorMoeda.format(valorTotalAReceber));
        dto.setTotalAprendizes(totalAprendizes);
        dto.setTotalSessoesRealizadas(totalSessoesRealizadas);
        dto.setTotalAusenciasJustificadas(totalAusenciasJustificadas);
        dto.setTotalAusenciasNaoJustificadas(totalAusenciasNaoJustificadas);

        return dto;
    }
}
