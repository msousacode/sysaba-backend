package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.AutenticacaoController;
import br.com.sysaba.modules.assinatura.Assinatura;
import br.com.sysaba.modules.assinatura.AssinaturaService;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/auth/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    private final UsuarioService usuarioService;
    private final AssinaturaService assinaturaService;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioService usuarioService, AssinaturaService assinaturaService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.assinaturaService = assinaturaService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping
    public ResponseEntity salvar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
            Usuario result = usuarioService.save(usuario);

            result.setTenantId(result.getUsuarioId());//Atualiza o tenantId corretamente.
            usuarioService.update(result.getUsuarioId(), result);

            Assinatura assinatura = Assinatura.getInstance(result);
            assinaturaService.save(assinatura);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
