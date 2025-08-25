package br.com.sysaba.modules.assinatura;

import br.com.sysaba.core.commons.service.GenericService;
import br.com.sysaba.core.enums.TipoAssinaturaEnum;
import br.com.sysaba.core.exception.RegistroNaoEncontradoException;
import br.com.sysaba.modules.assinatura.dto.ConfirmacaoPagamentoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AssinaturaService implements GenericService<Assinatura, UUID> {

    private final AssinaturaRepository assinaturaRepository;

    public AssinaturaService(AssinaturaRepository assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    @Override
    public JpaRepository<Assinatura, UUID> getRepository() {
        return assinaturaRepository;
    }

    @Override
    public Assinatura findById(UUID id) {
        return GenericService.super.findById(id);
    }

    @Override
    public void delete(UUID id) {
        GenericService.super.delete(id);
    }

    @Override
    public Assinatura save(Assinatura entity) {
        return GenericService.super.save(entity);
    }

    @Override
    public Assinatura update(UUID id, Assinatura entity) {
        return GenericService.super.update(id, entity);
    }

    @Override
    public Page<Assinatura> findAll(Pageable pageable) {
        return GenericService.super.findAll(pageable);
    }

    @Override
    public void beforeSave() {
        GenericService.super.beforeSave();
    }

    public void autualizaAssinaturaPaga(ConfirmacaoPagamentoDTO pagamentoDTO, UUID usuarioId) {

        Assinatura assinatura = assinaturaRepository.findByUsuario_usuarioId(usuarioId).orElseThrow(() -> new RegistroNaoEncontradoException("Não foi possível localizar o usuário para atualizar a assinatura de pagamento: " + pagamentoDTO.getEmail() + " + " + usuarioId));

        assinatura.setAtivo(true);
        assinatura.setTipoAssinatura(TipoAssinaturaEnum.ASSINANTE);
        assinatura.setDataContratacao(LocalDateTime.now());
        assinatura.setInvoiceStripeId(pagamentoDTO.getInvoiceId());
        assinatura.setStripeSubscriptionId(pagamentoDTO.getStripeSubscriptionId());

        assinaturaRepository.save(assinatura);
    }
}
