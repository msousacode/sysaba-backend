package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsDTO;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsColetaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/ablls")
public class AbllsController {

    private static final Logger logger = LoggerFactory.getLogger(AbllsController.class);

    private final AprendizService aprendizService;

    private final AbllsService abllsService;

    public AbllsController(AprendizService aprendizService, AbllsService abllsService) {
        this.aprendizService = aprendizService;
        this.abllsService = abllsService;
    }

    @Transactional
    @PostMapping("/avaliacoes")
    public ResponseEntity<UUID> salvar(@RequestBody AbllsDTO abllsDTO) {
        try {

            Aprendiz aprendiz = aprendizService.findById(abllsDTO.getAprendizId());

            AbllsAvaliacao avaliacao = AbllsAvaliacao.from(abllsDTO, aprendiz);

            AbllsAvaliacao result = abllsService.saveAvaliacao(avaliacao);

            return ResponseEntity.ok(result.getAbllsId());

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PostMapping("/usuario/{usuarioId}/avaliacao/{abllsId}/habilidade/{habilidade}")
    public ResponseEntity<?> coletas(@RequestBody List<AbllsColetaDTO> abllsColetaDTO, @PathVariable("usuarioId") UUID usuarioId, @PathVariable("abllsId") UUID abllsId, @PathVariable("habilidade") Integer habilidade) {

        abllsService.salvarColetas(abllsColetaDTO, usuarioId, abllsId, habilidade);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/coletas-respondidas/{abllsId}")
    public ResponseEntity<List<AbllsColetaDTO>> getColetasRespondidas(@PathVariable("abllsId") UUID abllsId) {
        List<AbllsColeta> coletas = abllsService.findByColetasRespondidas(abllsId);
        List<AbllsColetaDTO> dtos = coletas.stream().map(AbllsColetaDTO::of).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @GetMapping("/chart/pontuacoes/{abllsId}/habilidade/{habilidade}")
    public ResponseEntity<List<Integer>> getDataSetPontuacao(@PathVariable("abllsId") UUID portageId, @PathVariable("habilidade") Integer habilidade) {
        List<Integer> coletaPontuacoes = abllsService.findDataSetPontuacao(portageId, habilidade);
        return ResponseEntity.ok(coletaPontuacoes);
    }
}
