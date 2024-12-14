package br.com.sysaba.modules.avaliacoes;

import br.com.sysaba.modules.avaliacoes.portage.PortageAvaliacao;
import br.com.sysaba.modules.avaliacoes.portage.PortageService;
import br.com.sysaba.modules.avaliacoes.vbmapp.VBMappService;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappAvaliacao;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappColeta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoService {

    private final VBMappService vbMappService;

    private final PortageService portageService;

    public AvaliacaoService(VBMappService vbMappService, PortageService portageService) {
        this.vbMappService = vbMappService;
        this.portageService = portageService;
    }

    public List<AvaliacaoDTO> findAvaliacoes(UUID aprendizId) {
        List<AvaliacaoDTO> avaliacoes = new ArrayList<>();

        List<VbMappAvaliacao> vbMapps = vbMappService.findAllByAprendizId(aprendizId);

        List<PortageAvaliacao> portages = portageService.findAllByAprendizId(aprendizId);

        if (!vbMapps.isEmpty())
            avaliacoes.addAll(vbMapps.stream().map(v -> AvaliacaoDTO.of(v)).toList());

        if (!portages.isEmpty())
            avaliacoes.addAll(portages.stream().map(v -> AvaliacaoDTO.of(v)).toList());

        return avaliacoes;
    }
}
