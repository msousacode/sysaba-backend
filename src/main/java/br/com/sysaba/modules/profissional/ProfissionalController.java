package br.com.sysaba.modules.profissional;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionalController.class);

    private final UsuarioService usuarioService;

    public ProfissionalController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<ProfissionalDTO>> buscar(@PathVariable("tenantId") UUID tenantId) {
        try {
            List<Usuario> usuarioList = usuarioService.findAllByTenantId(tenantId);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioList.stream().map(i -> MapperUtil.converte(i, ProfissionalDTO.class)).toList());
        } catch (Exception ex) {
            logger.error("erro ao carregar lista de profissionais", ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<ProfissionalDTO> get(@PathVariable("email") String email) {
        try {
            Usuario usuario = usuarioService.getByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(MapperUtil.converte(usuario, ProfissionalDTO.class));
        } catch (Exception ex) {
            logger.error("erro ao carregar lista de profissionais", ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
