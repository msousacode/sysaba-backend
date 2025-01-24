package br.com.sysaba.modules.acesso;

import br.com.sysaba.core.security.CustomAuthenticationManager;
import br.com.sysaba.core.security.service.AuthService;
import br.com.sysaba.modules.acesso.dto.AuthDTO;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private CustomAuthenticationManager customAuthenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailService emailService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO.LoginRequest userLogin, HttpServletResponse response) {
        AuthDTO.Response auth;

        try {
            usuarioRepository.findByEmail(userLogin.username()).orElseThrow(() -> new UsernameNotFoundException(userLogin.username()));

            var authentication = customAuthenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.username(),
                            userLogin.password()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = authService.generateToken(authentication, userLogin.username());

            auth = new AuthDTO.Response("User logged in successfully", token);

        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("sysaba", "no-cache");
        return ResponseEntity.ok().body(auth.token());
    }

    @Modifying
    @Transactional
    @GetMapping("/esqueci/{email}")
    public ResponseEntity<?> esqueciMinhaSenha(@PathVariable String email) {
        try {

            String response = emailService.forget(email);

            if("NAO_ASSINANTE".equals(response)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            return "OK".equals(response) ? ResponseEntity.ok().body("Ok") : ResponseEntity.internalServerError().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
