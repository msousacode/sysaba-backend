package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.assinatura.Assinatura;
import br.com.sysaba.modules.assinatura.AssinaturaService;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/auth/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AssinaturaService assinaturaService;

    public UsuarioController(UsuarioService usuarioService, AssinaturaService assinaturaService) {
        this.usuarioService = usuarioService;
        this.assinaturaService = assinaturaService;
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void salvar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
            var result = usuarioService.save(usuario);
            Assinatura assinatura = Assinatura.getInstance(result);
            assinaturaService.save(assinatura);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
}
