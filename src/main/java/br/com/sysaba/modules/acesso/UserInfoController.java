package br.com.sysaba.modules.acesso;

import br.com.sysaba.core.security.config.RsaKeyConfigProperties;
import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.dto.UsuarioInfoDTO;
import br.com.sysaba.modules.acesso.dto.UsuarioInfoPerfilDTO;
import br.com.sysaba.modules.termo.Termo;
import br.com.sysaba.modules.termo.TermoRepository;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user-info")
public class UserInfoController {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);

    private final TermoRepository termoRepository;

    private final UsuarioRepository usuarioRepository;

    private final RsaKeyConfigProperties rsaKeyConfigProperties;

    public static final String HEADER_PREFIX = "Bearer ";

    public UserInfoController(TermoRepository termoRepository, UsuarioRepository usuarioRepository, RsaKeyConfigProperties rsaKeyConfigProperties) {
        this.termoRepository = termoRepository;
        this.usuarioRepository = usuarioRepository;
        this.rsaKeyConfigProperties = rsaKeyConfigProperties;
    }

    @GetMapping
    public ResponseEntity<UsuarioInfoDTO> get(HttpServletRequest request) {

        try {

            String authorizationHeader = resolveToken(request);

            JwtDecoder jwtDecoder = NimbusJwtDecoder.withPublicKey(rsaKeyConfigProperties.publicKey()).build();
            // Decodifica o token
            Jwt jwsClaims = jwtDecoder.decode(authorizationHeader);
            String email = (String) jwsClaims.getClaims().get("sub");

            Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

            UsuarioInfoDTO dto = MapperUtil.converte(usuario, UsuarioInfoDTO.class);

            return ResponseEntity.ok(dto);

        } catch (Exception e) {
            logger.error("Problems with JWT", e);
            throw new AuthenticationException("Problems with JWT", e.getCause()) {
            };
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @PutMapping
    public ResponseEntity<Void> updateUsuarioInfo(@RequestBody UsuarioInfoPerfilDTO usuarioInfoPerfilDTO) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioInfoPerfilDTO.getEmail());

        if(usuario.isPresent()) {
            usuario.get().setFullName(usuarioInfoPerfilDTO.getNome());
            usuario.get().setDocumento(usuarioInfoPerfilDTO.getDocumento());
            usuarioRepository.save(usuario.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
