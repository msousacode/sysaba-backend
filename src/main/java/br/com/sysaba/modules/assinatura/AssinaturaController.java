package br.com.sysaba.modules.assinatura;

import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import br.com.sysaba.modules.assinatura.dto.AssinaturaVerifyDTO;
import br.com.sysaba.modules.assinatura.dto.CancelamentoDTO;
import br.com.sysaba.modules.assinatura.dto.ConfirmacaoPagamentoDTO;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@RequestMapping("/api/auth/assinatura")
@RestController
public class AssinaturaController {

    private final AssinaturaRepository assinaturaRepository;

    private static final Logger logger = LoggerFactory.getLogger(AssinaturaController.class);

    private final AssinaturaService assinaturaService;

    private final UsuarioRepository usuarioRepository;

    public AssinaturaController(AssinaturaService assinaturaService, UsuarioRepository usuarioRepository, AssinaturaRepository assinaturaRepository) {
        this.assinaturaService = assinaturaService;
        this.usuarioRepository = usuarioRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    @Transactional
    @PostMapping("/pago")
    public ResponseEntity<?> confirmarPagamento(@RequestBody ConfirmacaoPagamentoDTO pagamentoDTO) {
        try {
            Usuario usuario = usuarioRepository.findByEmail(pagamentoDTO.getEmail().toLowerCase().trim()).orElseThrow(() -> new RegistroNaoEncontradoException("Não foi possível localizar email para confirmar o pagamento " + pagamentoDTO.getEmail()));
            assinaturaService.autualizaAssinaturaPaga(pagamentoDTO, usuario.getUsuarioId());
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            logger.error("Erro ao processar confirmação de pagamento para o usuário: " + pagamentoDTO.getEmail());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/verify/{email}")
    public ResponseEntity<AssinaturaVerifyDTO> verify(@PathVariable("email") String email) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty())
            return ResponseEntity.notFound().build();

        Assinatura assinatura = usuario.get().getAssinatura();

        if(assinatura == null) {
            return ResponseEntity.accepted().build();
        }

        AssinaturaVerifyDTO assinaturaDTO = new AssinaturaVerifyDTO();
        assinaturaDTO.setTipoAssinaturaEnum(assinatura.getTipoAssinatura());
        assinaturaDTO.setStripeSubscriptionId(assinatura.getStripeSubscriptionId());

        //Verifica se assinatura é do tipo TESTE     
        if(TipoAssinaturaEnum.TESTE.equals(assinatura.getTipoAssinatura())) {
            //Contabiliza a quantidade de dias para o fim do teste.
            Duration duration = Duration.between(assinatura.getCreatedAt(), LocalDateTime.now());
            //Retorna para o frontend a quantidade de dias que falta para o final do teste.
            long diasRestantesTeste = duration.toDays();
            assinaturaDTO.setDiasRestantesTeste(diasRestantesTeste);

            return ResponseEntity.ok().body(assinaturaDTO);
        }

        if (TipoAssinaturaEnum.ASSINANTE.equals(assinatura.getTipoAssinatura())) {
            return ResponseEntity.ok().body(assinaturaDTO);
        }
        
        if (TipoAssinaturaEnum.NAO_ASSINANTE.equals(assinatura.getTipoAssinatura())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(assinaturaDTO);
        }
        
        return ResponseEntity.notFound().build();        
    }

    @Transactional
    @PutMapping("/userId/{userId}/cancel")
    public void confirmarCancelamento(@PathVariable UUID userId) {
        try {
            Assinatura assinatura = assinaturaService.findByUserId(userId);
            assinatura.setDataCancelamento(LocalDateTime.now());
            assinatura.setTipoAssinatura(TipoAssinaturaEnum.CANCELADA);
    
            assinaturaService.save(assinatura);        

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}
