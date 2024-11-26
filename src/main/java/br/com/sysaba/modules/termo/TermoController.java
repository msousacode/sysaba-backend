package br.com.sysaba.modules.termo;

import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth/termo")
@RestController
public class TermoController {

    private static final Logger logger = LoggerFactory.getLogger(TermoController.class);

    private final TermoRepository termoRepository;

    private final UsuarioRepository usuarioRepository;

    public TermoController(TermoRepository termoRepository, UsuarioRepository usuarioRepository) {
        this.termoRepository = termoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<Boolean> aceite(@PathVariable("email") String email) {

        try {
            Usuario usuario = usuarioRepository.findByEmail(email).get();

            Termo termo = new Termo();
            termo.setAceite(true);
            termo.setUsuario(usuario);

            termoRepository.save(termo);

            return ResponseEntity.ok(true);
        } catch (RuntimeException e) {
            logger.error("Erro ao confirmar aceite", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
