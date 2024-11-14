package br.com.sysaba.modules.acesso;

import br.com.sysaba.core.security.service.AuthService;
import br.com.sysaba.modules.acesso.dto.AuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.LoginRequest userLogin) {
        AuthDTO.Response response = null;

        try {
            Authentication authentication =
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    userLogin.username(),
                                    userLogin.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = authService.generateToken(authentication);

            response = new AuthDTO.Response("User logged in successfully", token);

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(response);
    }
}
