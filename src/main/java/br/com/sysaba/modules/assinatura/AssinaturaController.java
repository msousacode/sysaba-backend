package br.com.sysaba.modules.assinatura;

import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import br.com.sysaba.modules.assinatura.dto.ConfirmacaoPagamentoDTO;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth/assinatura")
@RestController
public class AssinaturaController {

    private static final Logger logger = LoggerFactory.getLogger(AssinaturaController.class);

    private final AssinaturaService assinaturaService;

    private final UsuarioRepository usuarioRepository;

    public AssinaturaController(AssinaturaService assinaturaService, UsuarioRepository usuarioRepository) {
        this.assinaturaService = assinaturaService;
        this.usuarioRepository = usuarioRepository;
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
}
