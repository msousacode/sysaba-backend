package br.com.sysaba.modules.usuario;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.usuario.dtos.UsuarioDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public void salvar(UsuarioDTO usuarioDTO) throws RuntimeException {
        Usuario usuario = MapperUtil.converte(usuarioDTO, Usuario.class);
        usuarioService.save(usuario);
    }
}
