package br.com.sysaba.modules.vbmapp;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizController;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.vbmapp.dto.VbMappDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/vbmapp")
public class VBMappController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final VBMappService vbMappService;

    private final AprendizService aprendizService;

    public VBMappController(VBMappService vbMappService, AprendizService aprendizService) {
        this.vbMappService = vbMappService;
        this.aprendizService = aprendizService;
    }

    @Transactional
    @PostMapping("/avaliacoes")
    public ResponseEntity<UUID> salvar(@RequestBody VbMappDTO vbMappDTO) {
        try {
            Aprendiz aprendiz = aprendizService.findById(UUID.fromString(vbMappDTO.getAprendizUuidFk()));
            VbMappAvaliacao entity = VbMappAvaliacao.from(vbMappDTO, aprendiz);

            VbMappAvaliacao vbMappAvaliacao = vbMappService.saveAvaliacao(entity);

            return ResponseEntity.ok(vbMappAvaliacao.getVbMappId());

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/config-tela/{vbmappId}")
    public ResponseEntity<VbMappDTO> getConfigTela(@PathVariable("vbmappId") UUID vbmappId) {
        try {
            VbMappAvaliacao vbMapp = vbMappService.findById(vbmappId);
            VbMappDTO vbMappDTO = MapperUtil.converte(vbMapp, VbMappDTO.class);
            return ResponseEntity.ok(vbMappDTO);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}