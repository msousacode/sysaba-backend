package br.com.sysaba.modules.atendimento;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.atendimento.dto.AtendimentoDTO;
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
@RequestMapping(path = "/api/atendimentos")
public class AtendimentoController {

    private static final Logger logger = LoggerFactory.getLogger(AtendimentoController.class);

    private final AtendimentoService atendimentoService;

    private final AprendizService aprendizService;

    public AtendimentoController(AtendimentoService atendimentoService, AprendizService aprendizService) {
        this.atendimentoService = atendimentoService;
        this.aprendizService = aprendizService;
    }

    @Transactional
    @PostMapping("/aprendiz/{aprendizId}")
    public ResponseEntity<?> salvar(@RequestBody AtendimentoDTO atendimentoDTO, @PathVariable("aprendizId") UUID aprendizId) {
        try {
            Atendimento atendimento = MapperUtil.converte(atendimentoDTO, Atendimento.class);

            Aprendiz aprendiz = aprendizService.findById(aprendizId);
            atendimento.setAprendiz(aprendiz);
            atendimentoService.save(atendimento);
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
    public ResponseEntity<Page<AtendimentoDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Atendimento> atendimentoList = atendimentoService.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
        Page<AtendimentoDTO> dtoList = atendimentoList.map(i -> MapperUtil.converte(i, AtendimentoDTO.class));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> getAlvo(@PathVariable("id") UUID id) {
        Atendimento saved = atendimentoService.findById(id);
        AtendimentoDTO dto = MapperUtil.converte(saved, AtendimentoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
