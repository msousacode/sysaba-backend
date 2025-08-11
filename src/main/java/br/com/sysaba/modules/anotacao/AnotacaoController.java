package br.com.sysaba.modules.anotacao;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.alvo.Alvo;
import br.com.sysaba.modules.alvo.AlvoImport;
import br.com.sysaba.modules.alvo.AlvoImportRespository;
import br.com.sysaba.modules.alvo.AlvoService;
import br.com.sysaba.modules.anotacao.dto.AnotacaoDTO;
import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.atendimento.Atendimento;
import br.com.sysaba.modules.atendimento.AtendimentoService;
import br.com.sysaba.modules.coleta.Coleta;
import br.com.sysaba.modules.coleta.ColetaService;
import br.com.sysaba.modules.treinamento.Treinamento;
import br.com.sysaba.modules.treinamento.TreinamentoService;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/anotacoes")
public class AnotacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AnotacaoController.class);

    private final AnotacaoService anotacaoService;

    private final UsuarioService usuarioService;

    private final AlvoImportRespository alvoImportRespository;

    private final AprendizService aprendizService;

    public AnotacaoController(AnotacaoService anotacaoService, UsuarioService usuarioService, AlvoImportRespository alvoImportRespository, AprendizService aprendizService) {
        this.anotacaoService = anotacaoService;        
        this.usuarioService = usuarioService;
        this.alvoImportRespository = alvoImportRespository;
        this.aprendizService = aprendizService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody AnotacaoDTO anotacaoDTO) {
        try {

            Aprendiz aprendiz = aprendizService.findById(anotacaoDTO.getAprendizId());

            Anotacao anotacao = MapperUtil.converte(anotacaoDTO, Anotacao.class);            
            AlvoImport alvoImport = alvoImportRespository.findById(anotacaoDTO.getColetaId()).get();

            String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Usuario usuario = usuarioService.getByEmail(email);

            anotacao.setCriadoNome(usuario.getFullName());
            anotacao.setCriadoPor(usuario.getUsuarioId());
            anotacao.setAlvoImport(alvoImport);
            anotacao.setAprendiz(aprendiz);

            anotacaoService.save(anotacao);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity<AnotacaoDTO> atualizar(@RequestBody AnotacaoDTO anotacaoDTO) {
        try {

            String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            Usuario usuario = usuarioService.getByEmail(email);

            Anotacao anotacao = MapperUtil.converte(anotacaoDTO, Anotacao.class);
            anotacao.setCriadoNome(usuario.getFullName());

            anotacaoService.update(anotacaoDTO.getAnotacaoId(), anotacao);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }  

    @GetMapping("/v2/aprendiz/{aprendizId}")
    public ResponseEntity<Page<AnotacaoDTO>> buscarAnotacaoPorAprendiz(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @PathVariable("aprendizId") UUID aprendizId) {
        Page<Anotacao> anotacoes = anotacaoService.findByAprendiz_aprendizId(aprendizId, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Usuario usuario = usuarioService.getByEmail(email);

        Page<AnotacaoDTO> dtoList = anotacoes.map( i -> AnotacaoDTO.fromAnotacaoDTO(i, usuario.getUsuarioId()));
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDTO> getAlvo(@PathVariable("id") UUID id) {
        Anotacao saved = anotacaoService.findById(id);
        AnotacaoDTO dto = MapperUtil.converte(saved, AnotacaoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnotacao(@PathVariable("id") UUID id) {
        try {
            anotacaoService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
