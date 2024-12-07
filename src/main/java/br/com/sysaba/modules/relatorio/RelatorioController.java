package br.com.sysaba.modules.relatorio;

import br.com.sysaba.modules.relatorio.dto.RelatorioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/auth/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/aprendiz/{aprendizId}/dataInicio/{dataInicio}/dataFinal/{dataFinal}")
    public ResponseEntity<RelatorioDTO> getRelatorio(@PathVariable("aprendizId") UUID aprendizId, @PathVariable("dataInicio") String dataInicio, @PathVariable("dataFinal") String dataFinal) throws IOException {
        return ResponseEntity.ok().body(relatorioService.gerarRelatorio(aprendizId));
    }
}
