package br.com.sysaba.modules.relatorio;

import br.com.sysaba.modules.avaliacoes.ChartDTO;
import br.com.sysaba.modules.relatorio.dto.LinkDowloadResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/aprendiz/{aprendizId}/dataInicio/{dataInicio}/dataFinal/{dataFinal}")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorio(@PathVariable("aprendizId") UUID aprendizId, @PathVariable("dataInicio") String dataInicio, @PathVariable("dataFinal") String dataFinal) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.gerarRelatorio(aprendizId, dataInicio, dataFinal);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }

    @GetMapping("/portage/{portageId}")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorioPortage(@PathVariable("portageId") UUID portageId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.gerarRelatorioPorgate(portageId);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }

    @GetMapping("/portage/{portageId}/pei")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorioPortagePEI(@PathVariable("portageId") UUID portageId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.gerarRelatorioPorgatePEI(portageId);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }

    @GetMapping("/vbmapp/{vbmappId}/pei")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorioVbMappPEI(@PathVariable("vbmappId") UUID vbmappId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.getRelatorioVbMappPEI(vbmappId);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }


    @PostMapping("/vbmapp/aprendiz/{aprendizId}/barreiras")
    public ResponseEntity<LinkDowloadResponseDTO> getChartBarreiras(@PathVariable("aprendizId") UUID aprendizId, @RequestBody ChartDTO chartDTO) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.getRelatorioVbMappBarreiras(aprendizId, chartDTO);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }
}
