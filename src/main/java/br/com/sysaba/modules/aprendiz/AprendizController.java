package br.com.sysaba.modules.aprendiz;

import br.com.sysaba.core.security.config.TenantAuthenticationToken;
import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.aprendiz.dto.AprendizDTO;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/aprendizes")
public class AprendizController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final AprendizService aprendizService;

    private final UsuarioService usuarioService;

    private final AprendizProfissionalRespository aprendizProfissionalRespository;

    public AprendizController(AprendizService aprendizService, UsuarioService usuarioService, AprendizProfissionalRespository aprendizProfissionalRespository) {
        this.aprendizService = aprendizService;
        this.usuarioService = usuarioService;
        this.aprendizProfissionalRespository = aprendizProfissionalRespository;
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

    @Transactional
    @PutMapping
    public ResponseEntity<AprendizDTO> atualizar(@RequestBody AprendizDTO aprendizDTO) {
        try {
            Aprendiz aprendiz = MapperUtil.converte(aprendizDTO, Aprendiz.class);
            Aprendiz saved = aprendizService.update(aprendizDTO.getAprendizId(), aprendiz);
            AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<AprendizDTO>> buscar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {

        PerfilEnum perfilEnum = getPerfil();

        if (PerfilEnum.ADMIN.equals(perfilEnum)) {
            Page<Aprendiz> aprendizList = aprendizService.findAllIsTrue(PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));
            Page<AprendizDTO> dtoList = aprendizList.map(i -> MapperUtil.converte(i, AprendizDTO.class));
            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        }

        if (!PerfilEnum.ADMIN.equals(perfilEnum)) {
            UUID usuarioId = ((TenantAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getTenantId();

            List<AprendizProfissional> aprendizProfissionals = aprendizProfissionalRespository.findAllByProfissional_usuarioId(usuarioId);

            Page<Aprendiz> aprendizes = transformarParaPage(aprendizProfissionals, PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(direction), sort)));

            Page<AprendizDTO> dtoList = aprendizes.map(i -> MapperUtil.converte(i, AprendizDTO.class));

            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AprendizDTO> getAlvo(@PathVariable("id") UUID id) {
        Aprendiz saved = aprendizService.findById(id);
        AprendizDTO dto = MapperUtil.converte(saved, AprendizDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        try {
            aprendizService.inativar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            logger.error("erro ao deletar", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    private PerfilEnum getPerfil() {
        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return usuarioService.getByEmail(email).getPerfil();
    }

    public Page<Aprendiz> transformarParaPage(List<AprendizProfissional> aprendizProfissionals, Pageable pageable) {
        List<Aprendiz> aprendizes = aprendizProfissionals.stream()
                .map(this::converterParaAprendiz) // Método que você deve implementar
                .collect(Collectors.toList());

        return new PageImpl<>(aprendizes, pageable, aprendizProfissionals.size());
    }

    private Aprendiz converterParaAprendiz(AprendizProfissional profissional) {
        Aprendiz aprendiz = new Aprendiz();
        aprendiz.setAprendizId(profissional.getAprendiz().getAprendizId());
        aprendiz.setNomeAprendiz(profissional.getAprendiz().getNomeAprendiz());
        aprendiz.setNascAprendiz(profissional.getAprendiz().getNascAprendiz());
        aprendiz.setNomeMae(profissional.getAprendiz().getNomeMae());
        aprendiz.setNomePai(profissional.getAprendiz().getNomePai());
        aprendiz.setNomeResponsavel(profissional.getAprendiz().getNomeResponsavel());
        aprendiz.setAtivo(profissional.getAprendiz().getAtivo());
        return aprendiz;
    }
}
