package br.com.sysaba.modules.profissional;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.acesso.PerfilEnum;
import br.com.sysaba.modules.cargo.Cargo;
import br.com.sysaba.modules.cargo.CargoRespository;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profissionais")
public class ProfissionalController {

    private static final Logger logger = LoggerFactory.getLogger(ProfissionalController.class);

    private final UsuarioService usuarioService;

    private final CargoRespository cargoRespository;

    public ProfissionalController(UsuarioService usuarioService, CargoRespository cargoRespository) {
        this.usuarioService = usuarioService;
        this.cargoRespository = cargoRespository;
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<ProfissionalDTO>> buscar(@PathVariable("tenantId") UUID tenantId) {
        try {
            List<Usuario> usuarioList = usuarioService.findAllByTenantId(tenantId);


            List<ProfissionalDTO> profissionaisVinculados = new ArrayList<>();
            for (Usuario ap : usuarioList) {
                if(ap.getCargo() == null) {
                    ap.setCargo(new Cargo());
                }
                profissionaisVinculados.add(


                        new ProfissionalDTO(
                                ap.getFullName(),
                                ap.getEmail(),
                                ap.getPerfil().name(),
                                ap.getCargo().getCargoId()  == null ? UUID.randomUUID() : ap.getCargo().getCargoId(),
                                ap.getCargo().getDescricao() == null ? "Admin" : ap.getCargo().getDescricao()
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body(profissionaisVinculados);
        } catch (Exception ex) {
            logger.error("erro ao carregar lista de profissionais", ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<ProfissionalDTO> get(@PathVariable("email") String email) {
        try {
            Usuario usuario = usuarioService.getByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(MapperUtil.converte(usuario, ProfissionalDTO.class));
        } catch (Exception ex) {
            logger.error("erro ao carregar lista de profissionais", ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<ProfissionalDTO> atualizar(@RequestBody ProfissionalDTO profissionalDTO, @PathVariable("email") String email) {
        try {
            Usuario usuario = usuarioService.getByEmail(email);

            Cargo cargo = cargoRespository.findById(profissionalDTO.getCargo()).get();

            //usuario.setPerfil(PerfilEnum.getEnum(profissionalDTO.getPerfil()));
            usuario.setFullName(profissionalDTO.getFullName());
            usuario.setEmail(profissionalDTO.getEmail());
            usuario.setCargo(cargo);

            usuarioService.save(usuario);

            return ResponseEntity.status(HttpStatus.OK).body(MapperUtil.converte(usuario, ProfissionalDTO.class));
        } catch (Exception ex) {
            logger.error("erro ao carregar lista de profissionais", ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteProfessional(@PathVariable("email") String email) {

        try {
            Usuario usuario = usuarioService.getByEmail(email.trim());

            usuario.setEmail(usuario.getEmail() + "_old");
            usuario.setAtivo(false);

            usuarioService.save(usuario);

            return ResponseEntity.ok().build();

        } catch (Exception ex) {
            logger.error("Erro ao deletar profissional", ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
