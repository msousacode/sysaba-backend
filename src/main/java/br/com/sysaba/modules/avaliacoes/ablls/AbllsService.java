package br.com.sysaba.modules.avaliacoes.ablls;

import br.com.sysaba.modules.aprendiz.Aprendiz;
import br.com.sysaba.modules.aprendiz.AprendizService;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsColetaDTO;
import br.com.sysaba.modules.avaliacoes.ablls.dto.AbllsHabilidadeEnum;
import br.com.sysaba.modules.usuario.Usuario;
import br.com.sysaba.modules.usuario.UsuarioService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

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

        AbllsHabilidadeEnum habilidadeEnum = AbllsHabilidadeEnum.getByCod(habilidade);

        List<AbllsColeta> list = abllsColetaDTO.stream().map(item -> AbllsColeta.of(aprendiz, abllsAvaliacao, item, habilidadeEnum, usuario)).toList();

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

    public List<Integer> findDataSetPontuacao(UUID abllsId, Integer habilidade) {

        List<AbllsColeta> resultList = abllsColetaRepository.findByAblls_abllsIdAndHabilidade(abllsId, AbllsHabilidadeEnum.getByCod(habilidade));

        if (resultList.isEmpty()) {
            return List.of(0, 0);
        }

        List<Integer> coletaPontuacoes;

        coletaPontuacoes = new ArrayList<>(List.of(0, 0));

        Integer soma = 0;
        for (AbllsColeta num : resultList) {
            soma += num.getResposta() == -1 ? 0 : num.getResposta();
        }
        coletaPontuacoes.set(0, soma);

        coletaPontuacoes.set(1, getPontuacaoEsperada(resultList.stream().findFirst().get().getHabilidade()));

        return coletaPontuacoes;
    }

    private Integer getPontuacaoEsperada(AbllsHabilidadeEnum habilidade) {

        switch (habilidade) {
            case COOPERACAO_E_EFICACIA_DO_REFORCADOR, GRAMATICA_E_SINTAXE:
                return 44;
            case DESEMPENHO_VISUAL:
                return 102;
            case LINGUAGEM_RECEPTIVA, INTRAVERBAL:
                return 184;
            case IMITACAO_MOTORA, INTERACAO_SOCIAL:
                return 80;
            case IMITACAO_VOCAL:
                return 56;
            case SOLICITACOES:
                return 72;
            case NOMEACAO:
                return 154;
            case VOCALIZACOES_ESPONTANEAS, HABILIDADES_MOTORAS_FINAS:
                return 28;
            case JOGOS_E_LAZER:
                return 54;
            case INSTRUCOES_EM_GRUPO:
                return 36;
            case ROTINAS_CLASSE, USO_DO_BANHEIRO:
                return 24;
            case RESPOSTAS_GENERALIZADAS:
                return 12;
            case LEITURA:
                return 52;
            case MATEMATICA:
                return 76;
            case ESCRITA:
                return 34;
            case ORTOGRAFIA:
                return 18;
            case VESTIMENTA:
                return 32;
            case ALIMENTACAO:
                return 20;
            case PREPARACAO:
                return 14;
            case HABILIDADES_MOTORAS_GROSSAS:
                return 30;
        }
        return 0;
    }
}
