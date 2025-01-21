package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.AutenticacaoController;
import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.acesso.dto.AssinaturaDTO;
import br.com.sysaba.modules.acesso.dto.UsuarioInfoDTO;
import br.com.sysaba.modules.assinatura.Assinatura;
import br.com.sysaba.modules.assinatura.AssinaturaService;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api/auth/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    private final UsuarioService usuarioService;
    private final AssinaturaService assinaturaService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService, AssinaturaService assinaturaService, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.assinaturaService = assinaturaService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping("/tenant/{usuarioId}")
    public ResponseEntity<UsuarioInfoDTO> salvar(@RequestBody UsuarioDTO usuarioDTO, @PathVariable("usuarioId") UUID usuarioId) {
        try {
            Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha() == null ? UUID.randomUUID().toString() : usuario.getSenha()));
            PerfilEnum perfil = PerfilEnum.getEnum(usuarioDTO.getPerfil());
            usuario.setPerfil(perfil);
            Usuario result = usuarioService.save(usuario);

            if (!PerfilEnum.ADMIN.equals(perfil)) {
                Usuario tentant = usuarioService.findById(usuarioId);
                result.setTenantId(tentant.getUsuarioId());
                result.setCriadoPor(tentant.getUsuarioId());
                usuarioService.update(result.getUsuarioId(), result);
            } else {
                result.setTenantId(result.getUsuarioId());//Atualiza o tenantId corretamente.
                usuarioService.update(result.getUsuarioId(), result);
            }

            if (PerfilEnum.ADMIN.equals(perfil)) {
                Assinatura assinatura = Assinatura.getInstance(result);
                Assinatura assinaturaResult = assinaturaService.save(assinatura);

                UsuarioInfoDTO dto = new UsuarioInfoDTO();

                dto.setUsuarioId(result.getUsuarioId());
                dto.setFullName(result.getFullName());
                dto.setEmail(result.getEmail());
                AssinaturaDTO assinaturaDTO = new AssinaturaDTO(assinaturaResult.getAssinaturaId(), assinaturaResult.getTipoAssinatura().name(), assinaturaResult.getDataContratacao(), assinaturaResult.getAtivo());
                dto.setAssinatura(assinaturaDTO);
                return ResponseEntity.ok().body(dto);
            }

            return ResponseEntity.ok().build();

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/subscription/{email}")
    public ResponseEntity<UsuarioInfoDTO> getDadosBasiciosUsuario(@PathVariable("email") String email) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        UsuarioInfoDTO dto = new UsuarioInfoDTO();

        dto.setEmail(usuario.getEmail());
        dto.setFullName(usuario.getFullName());

        AssinaturaDTO assinaturaDTO = new AssinaturaDTO();
        assinaturaDTO.setAssinaturaId(usuario.getAssinatura().getAssinaturaId());
        assinaturaDTO.setTipoAssinatura(usuario.getAssinatura().getTipoAssinatura().name());
        assinaturaDTO.setAtivo(usuario.getAtivo());
        assinaturaDTO.setDataContratacao(usuario.getAssinatura().getDataContratacao());

        dto.setAssinatura(assinaturaDTO);

        return ResponseEntity.ok().body(dto);
    }
}
