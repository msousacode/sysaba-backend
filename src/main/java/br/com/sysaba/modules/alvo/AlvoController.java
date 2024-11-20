package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.alvo.dto.AlvoDTO;
import br.com.sysaba.modules.aprendiz.AprendizController;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import br.com.sysaba.modules.treinamento.dto.TreinamentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/alvos")
public class AlvoController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final TreinamentoService treinamentoService;

    private final AlvoService alvoService;

    public AlvoController(TreinamentoService treinamentoService, AlvoService alvoService) {
        this.treinamentoService = treinamentoService;
        this.alvoService = alvoService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<AlvoDTO> salvarAlvo(@RequestBody AlvoDTO alvoDTO) {
        try {
            Alvo alvo = MapperUtil.converte(alvoDTO, Alvo.class);
            Treinamento treinamento = treinamentoService.findById(UUID.fromString(alvoDTO.getTreinamentoUuidFk()));
            alvo.setTreinamento(treinamento);
            Alvo saved = alvoService.save(alvo);
            br.com.sysaba.modules.alvo.dto.AlvoDTO dto = MapperUtil.converte(saved, AlvoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AlvoDTO> atualizar(@RequestBody AlvoDTO alvoDTO) {
        try {
            Alvo saved = alvoService.findById(UUID.fromString(alvoDTO.getAlvoId()));
            saved.setNomeAlvo(alvoDTO.getNomeAlvo());
            saved.setPergunta(alvoDTO.getPergunta());
            saved.setDescricaoAlvo(alvoDTO.getDescricaoAlvo());

            Alvo updated = alvoService.update(saved.getAlvoId(), saved);
            AlvoDTO dto = MapperUtil.converte(updated, AlvoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<AlvoDTO>> getAlvosAll(@PathVariable("id") UUID id) {
        Treinamento treinamento = treinamentoService.findById(id);
        List<br.com.sysaba.modules.alvo.dto.AlvoDTO> dtoList = treinamento.getAlvos().stream().map(i -> MapperUtil.converte(i, AlvoDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> getAlvo(@PathVariable("id") UUID id) {
        var saved = treinamentoService.findById(id);
        TreinamentoDTO dto = MapperUtil.converte(saved, TreinamentoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/all/treinamento/{id}")
    public ResponseEntity<List<AlvoDTO>> getAlvosByTreinamento(@PathVariable("id") UUID id) {
        List<Alvo> list = alvoService.getAlvosByTreinamento(id);
        List<AlvoDTO> dtoList = list.stream().map(i -> MapperUtil.converte(i, AlvoDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }
}
