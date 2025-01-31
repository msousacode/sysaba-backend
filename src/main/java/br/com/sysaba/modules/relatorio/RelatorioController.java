package br.com.sysaba.modules.relatorio;

import br.com.sysaba.modules.relatorio.dto.LinkDowloadResponseDTO;
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
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorio(@PathVariable("aprendizId") UUID aprendizId, @PathVariable("dataInicio") String dataInicio, @PathVariable("dataFinal") String dataFinal) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.gerarRelatorio(aprendizId, dataInicio, dataFinal);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }

    @GetMapping("/portage/{portageId}")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorioPortage(@PathVariable("portageId") UUID portageId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.gerarRelatorioPortage(portageId);

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

    @GetMapping("/ablls/{abllsId}/pei")
    public ResponseEntity<LinkDowloadResponseDTO> getRelatorioAbllsPEI(@PathVariable("abllsId") UUID abllsId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.getRelatorioAbllsPEI(abllsId);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }

    @GetMapping("/vbmapp/aprendiz/{aprendizId}/barreiras")
    public ResponseEntity<LinkDowloadResponseDTO> getChartBarreiras(@PathVariable("aprendizId") UUID aprendizId) {
        LinkDowloadResponseDTO linkDowloadResponseDTO = relatorioService.getRelatorioVbMappBarreiras(aprendizId);

        if (linkDowloadResponseDTO == null)
            return ResponseEntity.internalServerError().build();

        return ResponseEntity.ok(linkDowloadResponseDTO);
    }
}
