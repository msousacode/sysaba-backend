package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.portage.dto.PortageDTO;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappAvaliacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
}