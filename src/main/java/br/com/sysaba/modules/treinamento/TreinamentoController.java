package br.com.sysaba.modules.treinamento;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.AprendizController;
import br.com.sysaba.modules.treinamento.base.TreinamentoBase;
import br.com.sysaba.modules.treinamento.dto.TreinamentoBaseDTO;
import br.com.sysaba.modules.treinamento.dto.TreinamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/treinamentos")
public class TreinamentoController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final TreinamentoService treinamentoService;

    private final TreinamentoBaseRespository treinamentoBaseRespository;

    public TreinamentoController(TreinamentoService treinamentoService, TreinamentoBaseRespository treinamentoBaseRespository) {
        this.treinamentoService = treinamentoService;
        this.treinamentoBaseRespository = treinamentoBaseRespository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TreinamentoDTO> salvar(@RequestBody TreinamentoDTO treinamentoDTO) {
        try {
            Treinamento treinamento = MapperUtil.converte(treinamentoDTO, Treinamento.class);
            Treinamento saved = treinamentoService.save(treinamento);
            TreinamentoDTO dto = MapperUtil.converte(saved, TreinamentoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity<TreinamentoDTO> atualizar(@RequestBody TreinamentoDTO treinamentoDTO) {
        try {
            Treinamento treinamento = MapperUtil.converte(treinamentoDTO, Treinamento.class);
            Treinamento saved = treinamentoService.update(UUID.fromString(treinamentoDTO.getTreinamentoId()), treinamento);
            TreinamentoDTO dto = MapperUtil.converte(saved, TreinamentoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<TreinamentoDTO>> getTreinamentosAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Treinamento> list = treinamentoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
        Page<TreinamentoDTO> dtoList = list.map(i -> MapperUtil.converte(i, TreinamentoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("{id}")
    public ResponseEntity<TreinamentoDTO> getTreinamento(@PathVariable("id") UUID id) {
        var saved = treinamentoService.findById(id);
        TreinamentoDTO dto = MapperUtil.converte(saved, TreinamentoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/base")
    public ResponseEntity<List<TreinamentoBaseDTO>> getTreinamentosBase() {
        List<TreinamentoBase> list = treinamentoBaseRespository.findAll();
        List<TreinamentoBaseDTO> listDto = list.stream().map(i -> MapperUtil.converte(i, TreinamentoBaseDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(listDto);
    }

    @Transactional
    @PostMapping("/base/aprendiz/{usuarioId}")
    public ResponseEntity<TreinamentoDTO> importar(@RequestBody List<UUID> treinamentosIds, @PathVariable("usuarioId") UUID usuarioId) {
        try {
            treinamentoService.importar(treinamentosIds, usuarioId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
