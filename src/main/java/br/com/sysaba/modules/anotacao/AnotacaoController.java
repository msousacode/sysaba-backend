package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.anotacao.dto.AnotacaoDTO;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.coleta.ColetaService;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.TreinamentoService;
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
@RequestMapping(path = "/api/anotacoes")
public class AnotacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AnotacaoController.class);

    private final AnotacaoService anotacaoService;

    private final TreinamentoService treinamentoService;

    private final ColetaService coletaService;

    public AnotacaoController(AnotacaoService anotacaoService, TreinamentoService treinamentoService, ColetaService coletaService) {
        this.anotacaoService = anotacaoService;
        this.treinamentoService = treinamentoService;
        this.coletaService = coletaService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AnotacaoDTO anotacaoDTO) {
        try {
            Anotacao anotacao = MapperUtil.converte(anotacaoDTO, Anotacao.class);
            Treinamento treinamento = treinamentoService.findById(anotacaoDTO.getTreinamentoId());
            Coleta coleta = coletaService.findColetaId(anotacaoDTO.getColetaId());

            anotacao.setTreinamento(treinamento);
            anotacao.setColeta(coleta);

            anotacaoService.save(anotacao);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AnotacaoDTO> atualizar(@RequestBody AnotacaoDTO anotacaoDTO) {
        try {
            Anotacao anotacao = MapperUtil.converte(anotacaoDTO, Anotacao.class);
            anotacaoService.update(anotacaoDTO.getAnotacaoId(), anotacao);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/treinamento/{treinamentoId}")
    public ResponseEntity<Page<AnotacaoDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @PathVariable("treinamentoId") UUID trinamentoId) {
        Page<Anotacao> anotacoes = anotacaoService.findByTreinamento_treinamentoId(trinamentoId, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
        Page<AnotacaoDTO> dtoList = anotacoes.map(AnotacaoDTO::fromAnotacaoDTO);
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> getAlvo(@PathVariable("id") UUID id) {
        Anotacao saved = anotacaoService.findById(id);
        AnotacaoDTO dto = MapperUtil.converte(saved, AnotacaoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
