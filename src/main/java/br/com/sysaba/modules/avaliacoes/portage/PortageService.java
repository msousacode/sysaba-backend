package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PortageService {

    private final PortageRepository portageRepository;
    private final PortageColetaRepository portageColetaRepository;

    public PortageService(PortageRepository portageRepository, PortageColetaRepository portageColetaRepository) {
        this.portageRepository = portageRepository;
        this.portageColetaRepository = portageColetaRepository;
    }

    public PortageAvaliacao saveAvaliacao(PortageAvaliacao portageAvaliacao) {
        return portageRepository.save(portageAvaliacao);
    }

    public PortageAvaliacao findById(UUID vbmappId) {
        return portageRepository.findById(vbmappId).orElseThrow(() -> new RuntimeException("Não foi possível localizar a avaliação."));
    }
}
