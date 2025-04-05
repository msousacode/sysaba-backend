package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.AutenticacaoController;
import br.com.sysaba.modules.acesso.EmailService;
import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.acesso.dto.AssinaturaDTO;
import br.com.sysaba.modules.acesso.dto.UsuarioInfoDTO;
import br.com.sysaba.modules.assinatura.Assinatura;
import br.com.sysaba.modules.assinatura.AssinaturaService;
import br.com.sysaba.modules.cargo.Cargo;
import br.com.sysaba.modules.cargo.CargoRespository;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/auth/usuarios")
public class UsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    private final UsuarioService usuarioService;
    private final AssinaturaService assinaturaService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;
    private final CargoRespository cargoRespository;

    public UsuarioController(UsuarioService usuarioService, AssinaturaService assinaturaService, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository, EmailService emailService, CargoRespository cargoRespository) {
        this.usuarioService = usuarioService;
        this.assinaturaService = assinaturaService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
        this.cargoRespository = cargoRespository;
    }

    @PostMapping
    public ResponseEntity<UsuarioInfoDTO> criarNovoUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return salvar(usuarioDTO, null);
    }

    @Transactional
    @PostMapping("/tenant/{usuarioId}")
    public ResponseEntity<UsuarioInfoDTO> salvar(@RequestBody UsuarioDTO usuarioDTO, @Nullable @PathVariable(value = "usuarioId") UUID usuarioId) {
        try {
            Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
            usuario.setSenha(passwordEncoder.encode(usuarioDTO.getSenha() == null ? String.valueOf(UUID.randomUUID()) : usuario.getSenha()));
            PerfilEnum perfil = PerfilEnum.getEnum(usuarioDTO.getPerfil());
            usuario.setPerfil(perfil == null && usuarioId == null ? PerfilEnum.ADMIN : perfil);
            Usuario result = usuarioService.save(usuario);

            if (!PerfilEnum.ADMIN.equals(result.getPerfil()) || !PerfilEnum.ADMIN_CHECKIN.equals(result.getPerfil())) {
                Usuario tentant = usuarioService.findById(usuarioId);
                Cargo cargo = cargoRespository.findById(usuarioDTO.getCargoId()).get();

                result.setTenantId(tentant.getUsuarioId());
                result.setCriadoPor(tentant.getUsuarioId());
                result.setCargo(cargo);

                usuarioService.update(result.getUsuarioId(), result);
                emailService.forget(usuarioDTO.getEmail().trim());
            } else {
                result.setTenantId(result.getUsuarioId());//Atualiza o tenantId corretamente.
                usuarioService.update(result.getUsuarioId(), result);
            }

            if (PerfilEnum.ADMIN.equals(result.getPerfil()) || !PerfilEnum.ADMIN_CHECKIN.equals(result.getPerfil())) {
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

        } catch (Exception ex) {
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

        if(PerfilEnum.ADMIN.equals(usuario.getPerfil())) {
            assinaturaDTO.setAssinaturaId(usuario.getAssinatura().getAssinaturaId());
            assinaturaDTO.setTipoAssinatura(usuario.getAssinatura().getTipoAssinatura().name());
            assinaturaDTO.setDataContratacao(usuario.getAssinatura().getDataContratacao());
        }

        assinaturaDTO.setAtivo(usuario.getAtivo());

        dto.setAssinatura(assinaturaDTO);

        return ResponseEntity.ok().body(dto);
    }
}
