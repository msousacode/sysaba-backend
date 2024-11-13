package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void salvar(@RequestBody UsuarioDTO usuarioDTO) throws RuntimeException {
        Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
        usuarioService.save(usuario);
    }
}
