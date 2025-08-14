package br.com.sysaba.modules.alvo;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.alvo.dto.AlvoDTO;
import br.com.sysaba.modules.alvo.dto.AlvoEstrelasDTO;
import br.com.sysaba.modules.alvo.dto.AlvoImportDTO;
import br.com.sysaba.modules.aprendiz.AprendizController;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @PostMapping("/v2/salvar")
    public ResponseEntity<AlvoDTO> salvarAlvoV2(@RequestBody AlvoDTO alvoDTO) {
        try {
            Alvo alvo = MapperUtil.converte(alvoDTO, Alvo.class);            
            Alvo saved = alvoService.save(alvo);
            br.com.sysaba.modules.alvo.dto.AlvoDTO dto = MapperUtil.converte(saved, AlvoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/v2/importar")
    public ResponseEntity<Void> importarObjetivos(@RequestBody AlvoImportDTO dto) {        
        alvoService.importarObjetivos(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/v2/all")
    public ResponseEntity<List<AlvoDTO>> getAlvosAllV2(
         @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction
    ) {
        Page<Alvo> resultList = alvoService.findAll(PageRequest.of(page, size, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.valueOf(direction), sort)));
        List<AlvoDTO> dtoList = resultList.stream().map(i -> MapperUtil.converte(i, AlvoDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/v2/importados/all/{aprendizId}")
    public ResponseEntity<List<AlvoImportDTO>> getAlvosImportadosAllV2(
         @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @PathVariable(value = "aprendizId") UUID aprendizId
    ) {
        Page<AlvoImportDTO> resultList = alvoService.findImportadosAll(PageRequest.of(page, size, org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.valueOf(direction), sort)), aprendizId);
        List<AlvoImportDTO> dtoList = resultList.stream().map(i -> MapperUtil.converte(i, AlvoImportDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AlvoDTO> atualizar(@RequestBody AlvoDTO alvoDTO) {
        try {
            Alvo saved = alvoService.findById(UUID.fromString(alvoDTO.getAlvoId()));
            saved.setNomeAlvo(alvoDTO.getNomeAlvo());
            saved.setPergunta(alvoDTO.getPergunta());
            saved.setDescricaoAlvo(alvoDTO.getDescricaoAlvo());
            saved.setTag(alvoDTO.getTag());

            Alvo updated = alvoService.update(saved.getAlvoId(), saved);
            AlvoDTO dto = MapperUtil.converte(updated, AlvoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PutMapping("/v2/concluir/{id}")
    public ResponseEntity<Void> concluir(@PathVariable("id") UUID id) {
        try {
            alvoService.concluir(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @Transactional
    @PutMapping("/v2/atualizar-estrelas")
    public ResponseEntity<Void> atualizarEstrelas(@RequestBody AlvoEstrelasDTO estrelas) {
        try {            
            alvoService.atualizarEstrelas(estrelas);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    
    @Transactional
    @PutMapping("/encerrar/aprendiz/{id}")
    public ResponseEntity<AprendizDTO> encerrar(@PathVariable UUID id) {
        try {            
            alvoService.encerrar(id);            
            return ResponseEntity.ok().build();
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
    public ResponseEntity<AlvoDTO> getAlvo(@PathVariable("id") UUID id) {
        //var saved = treinamentoService.findById(id);
        //TreinamentoDTO dto = MapperUtil.converte(saved, TreinamentoDTO.class);
        Alvo alvo = alvoService.findById(id);
        AlvoDTO dto = MapperUtil.converte(alvo, AlvoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @GetMapping("/all/treinamento/{id}")
    public ResponseEntity<List<AlvoDTO>> getAlvosByTreinamento(@PathVariable("id") UUID id) {
        List<Alvo> list = alvoService.getAlvosByTreinamento(id);
        List<AlvoDTO> dtoList = list.stream().map(i -> MapperUtil.converte(i, AlvoDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlvosByTreinamento(@PathVariable UUID id) {
        try{
            alvoService.deleteByAlvoId(id);
        } catch(RuntimeException e) {
            logger.error("Erro ocorrido: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.noContent().build();
    }
}
