package br.com.sysaba.modules.coleta;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.coleta.dto.ColetaDTO;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import br.com.sysaba.modules.treinamento.alvo.Alvo;
import br.com.sysaba.modules.treinamento.alvo.AlvoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/coletas")
public class ColetaController {

    private static final Logger logger = LoggerFactory.getLogger(ColetaController.class);

    private final AprendizService aprendizService;

    private final ColetaService coletaService;

    private final AlvoService alvoService;

    private final TreinamentoService treinamentoService;

    public ColetaController(AprendizService aprendizService, ColetaService coletaService, AlvoService alvoService, TreinamentoService treinamentoService) {
        this.aprendizService = aprendizService;
        this.coletaService = coletaService;
        this.alvoService = alvoService;
        this.treinamentoService = treinamentoService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ColetaDTO coletaDTO) {
        try {
            Coleta coleta = MapperUtil.converte(coletaDTO, Coleta.class);

            Alvo alvo = alvoService.findById(UUID.fromString(coletaDTO.getAlvo().getAlvoId()));
            Aprendiz aprendiz = aprendizService.findById(coletaDTO.getAprendizUuidFk());
            Treinamento treinamento = treinamentoService.findById(UUID.fromString(coletaDTO.getAlvo().getTreinamentoUuidFk()));

            coleta.setAlvo(alvo);
            coleta.setAprendiz(aprendiz);
            coleta.setTreinamento(treinamento);

            coletaService.save(coleta);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AprendizDTO> atualizar(@RequestBody ColetaDTO coletaDTO) {
        try {
            Coleta coleta = MapperUtil.converte(coletaDTO, Coleta.class);
            Coleta saved = coletaService.update(coletaDTO.getUuid(), coleta);
            AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ColetaDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Coleta> aprendizList = coletaService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
        Page<ColetaDTO> dtoList = aprendizList.map(i -> MapperUtil.converte(i, ColetaDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColetaDTO> get(@PathVariable("id") UUID id) {
        Coleta saved = coletaService.findById(id);
        ColetaDTO dto = MapperUtil.converte(saved, ColetaDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
