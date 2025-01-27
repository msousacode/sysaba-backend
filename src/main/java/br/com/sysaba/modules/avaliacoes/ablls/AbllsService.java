package br.com.sysaba.modules.avaliacoes.ablls;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AbllsService {

    private final AbllsRespository abllsRespository;

    public AbllsService(AbllsRespository abllsRespository) {
        this.abllsRespository = abllsRespository;
    }

    public AbllsAvaliacao saveAvaliacao(AbllsAvaliacao avaliacao) {
        return abllsRespository.save(avaliacao);
    }

    public List<AbllsAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return abllsRespository.findAllByAprendiz_aprendizId(aprendizId);
    }
}
