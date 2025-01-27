package br.com.sysaba.modules.avaliacoes;

import br.com.sysaba.core.security.config.TenantAuthenticationToken;
import br.com.sysaba.modules.avaliacoes.ablls.AbllsAvaliacao;
import br.com.sysaba.modules.avaliacoes.ablls.AbllsService;
import br.com.sysaba.modules.avaliacoes.portage.PortageAvaliacao;
import br.com.sysaba.modules.avaliacoes.portage.PortageService;
import br.com.sysaba.modules.avaliacoes.vbmapp.VBMappService;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappAvaliacao;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AvaliacaoService {

    private final VBMappService vbMappService;

    private final PortageService portageService;

    private final AbllsService abllsService;

    public AvaliacaoService(VBMappService vbMappService, PortageService portageService, AbllsService abllsService) {
        this.vbMappService = vbMappService;
        this.portageService = portageService;
        this.abllsService = abllsService;
    }

    public List<AvaliacaoDTO> findAvaliacoes(UUID aprendizId) {
        List<AvaliacaoDTO> avaliacoes = new ArrayList<>();

        var usuarioId = ((TenantAuthenticationToken) SecurityContextHolder.getContext().getAuthentication());

        List<VbMappAvaliacao> vbMapps = vbMappService.findAllByAprendizId(aprendizId);

        List<PortageAvaliacao> portages = portageService.findAllByAprendizId(aprendizId);

        List<AbllsAvaliacao> ablls = abllsService.findAllByAprendizId(aprendizId);

        if (!vbMapps.isEmpty())
            avaliacoes.addAll(getVbMappAvaliacoes(vbMapps));

        if (!portages.isEmpty())
            avaliacoes.addAll(getPortageAvaliacoes(portages));

        if (!ablls.isEmpty())
            avaliacoes.addAll(getAbllsAvaliacoes(ablls));

        return avaliacoes;
    }

    private List<AvaliacaoDTO> getVbMappAvaliacoes(List<VbMappAvaliacao> list) {
        List<AvaliacaoDTO> dtos = new ArrayList<>();

        for (VbMappAvaliacao vbMappAvaliacao : list) {
            AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

            avaliacaoDTO.setId(vbMappAvaliacao.getVbMappId());
            avaliacaoDTO.setProtocolo(vbMappAvaliacao.getProtocolo());
            avaliacaoDTO.setTipo(vbMappAvaliacao.getNiveisColeta());

            Integer respondidas = vbMappService.findColetasRespondidas(vbMappAvaliacao.getVbMappId());

            Integer totalObjetivos = "1".equals(vbMappAvaliacao.getNiveisColeta()) ? 45 : "2".equals(vbMappAvaliacao.getNiveisColeta()) ? 60 : "3".equals(vbMappAvaliacao.getNiveisColeta()) ? 70 : 0;
            Integer naoRespondidas = totalObjetivos - respondidas;

            String porcentagem = calculaPorcentagem(respondidas, naoRespondidas);

            avaliacaoDTO.setColeta(respondidas + "/" + totalObjetivos);
            avaliacaoDTO.setProgresso(porcentagem);

            dtos.add(avaliacaoDTO);
        }
        return dtos;
    }

    private List<AvaliacaoDTO> getPortageAvaliacoes(List<PortageAvaliacao> list) {
        List<AvaliacaoDTO> dtos = new ArrayList<>();

        for (PortageAvaliacao vbMappAvaliacao : list) {
            AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

            avaliacaoDTO.setId(vbMappAvaliacao.getPortageId());
            avaliacaoDTO.setProtocolo(vbMappAvaliacao.getProtocolo());
            avaliacaoDTO.setTipo(vbMappAvaliacao.getIdadesColeta());

            Integer respondidas = portageService.findColetasRespondidas(vbMappAvaliacao.getPortageId());

            Integer totalObjetivos = 488;
            Integer naoRespondidas = totalObjetivos - respondidas;

            String porcentagem = calculaPorcentagem(respondidas, naoRespondidas);

            avaliacaoDTO.setColeta(respondidas + "/" + totalObjetivos);
            avaliacaoDTO.setProgresso(porcentagem);

            dtos.add(avaliacaoDTO);
        }
        return dtos;
    }

    private List<AvaliacaoDTO> getAbllsAvaliacoes(List<AbllsAvaliacao> list) {
        List<AvaliacaoDTO> dtos = new ArrayList<>();

        for (AbllsAvaliacao vbMappAvaliacao : list) {
            AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO();

            avaliacaoDTO.setId(vbMappAvaliacao.getAbllsId());
            avaliacaoDTO.setProtocolo(vbMappAvaliacao.getProtocolo());
            avaliacaoDTO.setTipo(vbMappAvaliacao.getHabilidade());

            //Integer respondidas = portageService.findColetasRespondidas(vbMappAvaliacao.getPortageId());

            //Integer totalObjetivos = 488;
            //Integer naoRespondidas = totalObjetivos - respondidas;

            //String porcentagem = calculaPorcentagem(respondidas, naoRespondidas);

            //avaliacaoDTO.setColeta(respondidas + "/" + totalObjetivos);
            //avaliacaoDTO.setProgresso(porcentagem);

            dtos.add(avaliacaoDTO);
        }
        return dtos;
    }

    private String calculaPorcentagem(Integer respondidas, Integer naoRespondidas) {
        if (respondidas == 0) return "0%";
        Integer totalObjetivos = respondidas + naoRespondidas;
        Double resultado = Double.valueOf((respondidas * 100) / totalObjetivos);
        return resultado + "%";
    }

    public void deleteAvaliacao(UUID avaliacaoId, String protocolo) {
        if ("vbmapp".equals(protocolo)) {
            vbMappService.deleteAvaliacao(avaliacaoId);
        }

        if ("portage".equals(protocolo)) {
            portageService.deleteAvaliacao(avaliacaoId);
        }
    }
}
