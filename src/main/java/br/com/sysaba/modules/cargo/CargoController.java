package br.com.sysaba.modules.cargo;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.aprendiz.AprendizController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/cargos")
public class CargoController {

    private static final Logger logger = LoggerFactory.getLogger(AprendizController.class);

    private final CargoRespository cargoRespository;

    public CargoController(CargoRespository cargoRespository) {
        this.cargoRespository = cargoRespository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody CargoDTO cargoDTO) {
        try {
            Cargo cargo = MapperUtil.converte(cargoDTO, Cargo.class);
            cargoRespository.save(cargo);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CargoDTO>> getAll() {
        List<Cargo> cargos = cargoRespository.findAllByAtivoIsTrue();
        List<CargoDTO> dtoList = cargos.stream().map(i -> MapperUtil.converte(i, CargoDTO.class)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CargoDTO> getById(@PathVariable("id") UUID id) {
        var saved = cargoRespository.findById(id);
        CargoDTO dto = MapperUtil.converte(saved, CargoDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<CargoDTO> atualizar(@RequestBody CargoDTO cargoDTO) {
        try {
            Cargo saved = cargoRespository.findById(cargoDTO.getCargoId()).get();

            saved.setDescricao(cargoDTO.getDescricao());
            saved.setPreco(cargoDTO.getPreco());
            saved.setAtivo(true);

            Cargo updated = cargoRespository.save(saved);

            CargoDTO dto = MapperUtil.converte(updated, CargoDTO.class);
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Transactional
    @Modifying
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        try {
            Cargo saved = cargoRespository.findById(id).get();
            saved.setAtivo(false);
            cargoRespository.save(saved);
            return ResponseEntity.ok().build();
        } catch (RuntimeException ex) {
            logger.error("Erro ocorrido: {}", ex.getMessage(), ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}
