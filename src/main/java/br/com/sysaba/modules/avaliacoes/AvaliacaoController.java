package br.com.sysaba.modules.avaliacoes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/avaliacoes")
public class AvaliacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoController.class);

    @Autowired
    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/aprendiz/{aprendizId}")
    public ResponseEntity<List<AvaliacaoDTO>> getAvaliacoes(@PathVariable("aprendizId") UUID aprendizId) {
        try {
            List<AvaliacaoDTO> vbMapp = avaliacaoService.findAvaliacoes(aprendizId);
            return ResponseEntity.ok(vbMapp);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @DeleteMapping("/{avaliacaoId}/protocolo/{protocolo}")
    public ResponseEntity<?> deleteAvaliacao(@PathVariable("avaliacaoId") UUID avaliacaoId, @PathVariable("protocolo") String protocolo) {
        try {
            avaliacaoService.deleteAvaliacao(avaliacaoId, protocolo);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido ao deletar a avaliação: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
