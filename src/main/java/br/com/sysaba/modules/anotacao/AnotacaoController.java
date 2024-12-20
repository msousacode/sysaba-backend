package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.anotacao.dto.AnotacaoDTO;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.AtendimentoService;
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

    private final AtendimentoService atendimentoService;

    private final TreinamentoService treinamentoService;

    private final ColetaService coletaService;

    public AnotacaoController(AnotacaoService anotacaoService, ColetaService coletaService, AtendimentoService atendimentoService, TreinamentoService treinamentoService) {
        this.anotacaoService = anotacaoService;
        this.coletaService = coletaService;
        this.atendimentoService = atendimentoService;
        this.treinamentoService = treinamentoService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AnotacaoDTO anotacaoDTO) {
        try {
            Anotacao anotacao = MapperUtil.converte(anotacaoDTO, Anotacao.class);
            Atendimento atendimento = atendimentoService.findById(anotacaoDTO.getAtendimentoId());
            Treinamento treinamento = treinamentoService.findById(anotacaoDTO.getTreinamentoId());
            Coleta coleta = coletaService.findColetaId(anotacaoDTO.getColetaId());

            anotacao.setAtendimento(atendimento);
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

    @GetMapping("/atendimento/{atendimentoId}/treinamento/{treinamentoId}")
    public ResponseEntity<Page<AnotacaoDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @PathVariable("atendimentoId") UUID atendimentoId,
            @PathVariable("treinamentoId") UUID treinamentoId) {
        Page<Anotacao> anotacoes = anotacaoService.findByAtendimento_atendimentoId(atendimentoId, treinamentoId, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
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
