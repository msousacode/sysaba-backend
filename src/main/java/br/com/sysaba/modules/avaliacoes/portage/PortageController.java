package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.portage.dto.PortageColetaDTO;
import br.com.sysaba.modules.avaliacoes.portage.dto.PortageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/portage")
public class PortageController {

    private static final Logger logger = LoggerFactory.getLogger(PortageController.class);

    private final PortageService portageService;

    private final AprendizService aprendizService;

    public PortageController(PortageService portageService, AprendizService aprendizService) {
        this.portageService = portageService;
        this.aprendizService = aprendizService;
    }

    @Transactional
    @PostMapping("/avaliacoes")
    public ResponseEntity<UUID> salvar(@RequestBody PortageDTO portageDTO) {
        try {
            Aprendiz aprendiz = aprendizService.findById(portageDTO.getAprendizId());
            PortageAvaliacao entity = PortageAvaliacao.from(portageDTO, aprendiz);

            PortageAvaliacao result = portageService.saveAvaliacao(entity);

            return ResponseEntity.ok(result.getPortageId());

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @PostMapping("/coletas")
    public ResponseEntity<UUID> salvarColeta(@RequestBody List<br.com.sysaba.modules.avaliacoes.portage.dto.PortageColetaDTO> portageDTOList) {
        try {
            PortageAvaliacao portageAvaliacao = portageService.findById(portageDTOList.get(0).getPortageUuidFk());

            Aprendiz aprendiz = aprendizService.findById(portageDTOList.get(0).getAprendizUuidFk());

            List<PortageColeta> portageColetaList = portageDTOList.stream().map(i -> PortageColeta.of(i, portageAvaliacao, aprendiz)).toList();

            portageService.saveColetaAvaliacao(portageColetaList);

            return ResponseEntity.ok(portageAvaliacao.getPortageId());

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/config-tela/{portageId}")
    public ResponseEntity<PortageAvaliacao> getConfigTela(@PathVariable("portageId") UUID portageId) {
        try {
            PortageAvaliacao vbMapp = portageService.findById(portageId);
            return ResponseEntity.ok().body(vbMapp);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/aprendiz/{aprendizId}")
    public ResponseEntity<List<PortageAvaliacao>> getVbMaapAprendiz(@PathVariable("aprendizId") UUID aprendizId) {
        try {
            List<PortageAvaliacao> vbMapp = portageService.findAllByAprendizId(aprendizId);
            return ResponseEntity.ok(vbMapp);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/coletas-respondidas/{portageId}")
    public ResponseEntity<List<PortageColetaDTO>> getColetasRespondidas(@PathVariable("portageId") UUID portageId) {
        List<PortageColeta> coletas = portageService.findByColetasRespondidas(portageId);
        List<PortageColetaDTO> dtos = coletas.stream().map(PortageColetaDTO::of).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    @GetMapping("/chart/pontuacoes/{portageId}")
    public ResponseEntity<List<Double>> getDataSetPontuacaoPortage(@PathVariable("portageId") UUID portageId) {
        List<Double> coletaPontuacoes = portageService.findDataSetPontuacaoPortage(portageId);
        return ResponseEntity.ok(coletaPontuacoes);
    }
}