package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
}
