package br.com.sysaba.core.security.service;


import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private UsuarioRepository usuarioRepository;

    private final JwtEncoder jwtEncoder;

    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtEncoder jwtEncoder, PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public String generateToken(Authentication authentication, String username) {

        Usuario usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("username", username);
        customClaims.put("tenantId", usuario.getUsuarioId());
        customClaims.put("perfil", "ADMIN");

        Instant now = Instant.now();

        /**
         * TODO usar quando for necessário não apagar
        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        */

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.DAYS))
                .subject(authentication.getName())
                //.claim("scope", scope)
                .claims(claims -> claims.putAll(customClaims))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

}
