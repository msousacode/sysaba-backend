package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
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
@RequestMapping(path = "/api/aprendizes")
public class AprendizController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final AprendizService aprendizService;

    public AprendizController(AprendizService aprendizService) {
        this.aprendizService = aprendizService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AprendizDTO aprendizDTO) {
        try {
            Aprendiz aprendiz = MapperUtil.converte(aprendizDTO, Aprendiz.class);
            aprendizService.save(aprendiz);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AprendizDTO> atualizar(@RequestBody AprendizDTO aprendizDTO) {
        try {
            Aprendiz aprendiz = MapperUtil.converte(aprendizDTO, Aprendiz.class);
            Aprendiz saved = aprendizService.update(aprendizDTO.getAprendizId(), aprendiz);
            AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AprendizDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Aprendiz> aprendizList = aprendizService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
        Page<AprendizDTO> dtoList = aprendizList.map(i -> MapperUtil.converte(i, AprendizDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AprendizDTO> getAlvo(@PathVariable("id") UUID id) {
        Aprendiz saved = aprendizService.findById(id);
        AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
