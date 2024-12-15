package br.com.sysaba.modules.relatorio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/aprendiz/{aprendizId}/dataInicio/{dataInicio}/dataFinal/{dataFinal}")
    public ResponseEntity<?> getRelatorio(@PathVariable("aprendizId") UUID aprendizId, @PathVariable("dataInicio") String dataInicio, @PathVariable("dataFinal") String dataFinal) {
        Boolean relatorio = relatorioService.gerarRelatorio(aprendizId, dataInicio, dataFinal);
        return relatorio ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
