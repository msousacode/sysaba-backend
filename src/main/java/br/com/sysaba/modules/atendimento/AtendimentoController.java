package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.security.config.TenantAuthenticationToken;
import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizProfissional;
import br.com.sysaba.modules.aprendiz.AprendizProfissionalRespository;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.atendimento.dto.AtendimentoDTO;
import br.com.sysaba.modules.atendimento.dto.ConfiguracoesDTO;
import br.com.sysaba.modules.atendimento.dto.TreinamentoItemDTO;
import br.com.sysaba.modules.treinamento.*;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/atendimentos")
public class AtendimentoController {

    private static final Logger logger = LoggerFactory.getLogger(AtendimentoController.class);

    private final AtendimentoService atendimentoService;

    private final AprendizService aprendizService;

    private final TreinamentoService treinamentoService;

    private final TreinamentoAtendimentoRepository treinamentoAtendimentoRepository;

    private final ConfiguracoesRepository configuracoesRepository;

    private final AprendizProfissionalRespository aprendizProfissionalRespository;

    private final UsuarioService usuarioService;

    public AtendimentoController(AtendimentoService atendimentoService, AprendizService aprendizService, TreinamentoService treinamentoService, TreinamentoAtendimentoRepository treinamentoAtendimentoRepository, ConfiguracoesRepository configuracoesRepository, AprendizProfissionalRespository aprendizProfissionalRespository, UsuarioService usuarioService) {
        this.atendimentoService = atendimentoService;
        this.aprendizService = aprendizService;
        this.treinamentoService = treinamentoService;
        this.treinamentoAtendimentoRepository = treinamentoAtendimentoRepository;
        this.configuracoesRepository = configuracoesRepository;
        this.aprendizProfissionalRespository = aprendizProfissionalRespository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    @PostMapping("/aprendiz/{aprendizId}")
    public ResponseEntity<?> salvar(@RequestBody AtendimentoDTO atendimentoDTO, @PathVariable("aprendizId") UUID aprendizId) {
        try {
            Atendimento atendimento = MapperUtil.converte(atendimentoDTO, Atendimento.class);

            Aprendiz aprendiz = aprendizService.findById(aprendizId);
            atendimento.setAprendiz(aprendiz);

            Atendimento atendimentoSaved = atendimentoService.save(atendimento);

            List<Treinamento> treinamentoList = atendimentoDTO.getTreinamentos().stream().map(i -> treinamentoService.findById(i.getTreinamentoId())).toList();

            List<TreinamentoAtendimento> treinamentoAtendimento = treinamentoList.stream().map(i -> new TreinamentoAtendimento(i, atendimentoSaved)).toList();
            List<TreinamentoAtendimento> treinamentoAtendimentoSaved = treinamentoAtendimentoRepository.saveAll(treinamentoAtendimento);


            List<Configuracoes> configuracoes = new ArrayList<>();
            atendimentoDTO.getTreinamentos().forEach(dto -> {
                treinamentoAtendimentoSaved.stream()
                        .filter(ta -> ta.getTreinamento().getTreinamentoId().equals(dto.getTreinamentoId())).findFirst()
                        .map(ta -> {
                            Configuracoes config = MapperUtil.converte(dto.getConfiguracoes(), Configuracoes.class);
                            config.setTreinamentoAtendimento(ta);
                            configuracoes.add(config);
                            return null;
                        });
            });
            configuracoesRepository.saveAll(configuracoes);

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AprendizDTO> atualizar(@RequestBody AtendimentoDTO atendimentoDTO) {
        try {
            Atendimento atendimento = MapperUtil.converte(atendimentoDTO, Atendimento.class);
            Atendimento saved = atendimentoService.update(UUID.fromString(atendimentoDTO.getUuid()), atendimento);
            AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AtendimentoDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        PerfilEnum perfilEnum = getPerfil();

        if (PerfilEnum.ADMIN.equals(perfilEnum)) {
            Page<Atendimento> atendimentoList = atendimentoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));

            Page<AtendimentoDTO> fromList = atendimentoList.map(AtendimentoDTO::fromAtendimentoList);

            return ResponseEntity.status(HttpStatus.OK).body(fromList);
        }

        if (!PerfilEnum.ADMIN.equals(perfilEnum)) {
            String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UUID usuarioId = usuarioService.getByEmail(email).getUsuarioId();

            List<AprendizProfissional> aprendizProfissionals = aprendizProfissionalRespository.findAllByProfissional_usuarioId(usuarioId);

            Page<Atendimento> atendimentoList = transformarParaPage(aprendizProfissionals, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));

            Page<AtendimentoDTO> fromList = atendimentoList.map(AtendimentoDTO::fromAtendimentoList);

            return ResponseEntity.status(HttpStatus.OK).body(fromList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> get(@PathVariable("id") UUID id) {
        Atendimento saved = atendimentoService.findById(id);
        AtendimentoDTO dto = AtendimentoDTO.fromAtendimento(saved);

        List<TreinamentoItemDTO> treinamentoItemDTOArrayList = new ArrayList<>();
        saved.getTreinamentoAtendimentos().forEach(i -> {
            TreinamentoItemDTO treinamentoItemDTOS = MapperUtil.converte(i.getTreinamento(), TreinamentoItemDTO.class);
            ConfiguracoesDTO configuracoesDTO = MapperUtil.converte(i.getConfiguracoes(), ConfiguracoesDTO.class);
            treinamentoItemDTOS.setConfiguracoes(configuracoesDTO);
            treinamentoItemDTOArrayList.add(treinamentoItemDTOS);
        });
        dto.setTreinamentos(treinamentoItemDTOArrayList);

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    private PerfilEnum getPerfil() {
        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return usuarioService.getByEmail(email).getPerfil();
    }

    public Page<Atendimento> transformarParaPage(List<AprendizProfissional> aprendizProfissionals, Pageable pageable) {
        List<Atendimento> aprendizes = aprendizProfissionals.stream()
                .map(this::converterParaAprendiz) // Método que você deve implementar
                .collect(Collectors.toList());

        return new PageImpl<>(aprendizes, pageable, aprendizProfissionals.size());
    }

    private Atendimento converterParaAprendiz(AprendizProfissional profissional) {
        Atendimento atendimento = new Atendimento();
        atendimento.setAprendiz(profissional.getAprendiz());

        Atendimento atendimentoResult = atendimentoService.findByAprendiz_aprendizId(profissional.getAprendiz().getAprendizId());

        atendimento.setAtendimentoId(atendimentoResult.getAtendimentoId());
        atendimento.setDataInicio(atendimentoResult.getDataInicio());
        atendimento.setAnotacoes(atendimentoResult.getAnotacoes());
        atendimento.setTreinamentoAtendimentos(atendimentoResult.getTreinamentoAtendimentos());

        return atendimento;
    }
}
