package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsColetaDTO;
import br.com.sysaba.modules.avaliacoes.portage.PortageColeta;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AbllsService {

    private final AbllsRespository abllsRespository;

    private final AbllsColetaRepository abllsColetaRepository;

    private final AprendizService aprendizService;

    private final UsuarioService usuarioService;

    public AbllsService(AbllsRespository abllsRespository, AbllsColetaRepository abllsColetaRepository, AprendizService aprendizService, UsuarioService usuarioService) {
        this.abllsRespository = abllsRespository;
        this.abllsColetaRepository = abllsColetaRepository;
        this.aprendizService = aprendizService;
        this.usuarioService = usuarioService;
    }

    public AbllsAvaliacao saveAvaliacao(AbllsAvaliacao avaliacao) {
        return abllsRespository.save(avaliacao);
    }

    public List<AbllsAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return abllsRespository.findAllByAprendiz_aprendizId(aprendizId);
    }

    public void salvarColetas(List<AbllsColetaDTO> abllsColetaDTO, UUID usuarioId, UUID abllsId, Integer habilidade) {

        Aprendiz aprendiz = aprendizService.findById(usuarioId);

        AbllsAvaliacao abllsAvaliacao = abllsRespository.findById(abllsId).get();

        String email = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        Usuario usuario = usuarioService.getByEmail(email);

        List<AbllsColeta> list = abllsColetaDTO.stream().map(item -> AbllsColeta.of(aprendiz, abllsAvaliacao, item, habilidade, usuario)).toList();

        List<Integer> coletasIds = list.stream().map(i -> i.getColetaId()).toList();

        List<AbllsColeta> coletasRespondidas = abllsColetaRepository.findAllColetasRespondidas(coletasIds);

        if (!coletasRespondidas.isEmpty()) {

            List<Integer> ids = coletasRespondidas.stream().map(i -> i.getColetaId()).distinct().toList();

            coletasRespondidas.forEach(i -> abllsColetaRepository.deleteAllByColetaId(ids));
        }

        abllsColetaRepository.saveAll(list);
    }

    public List<AbllsColeta> findByColetasRespondidas(UUID abllsId) {
        return abllsColetaRepository.findByAblls_abllsId(abllsId);
    }
}
