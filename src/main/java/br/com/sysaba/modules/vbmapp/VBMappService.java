package br.com.sysaba.modules.vbmapp;

import br.com.sysaba.modules.vbmapp.repository.VBMappColetaRepository;
import br.com.sysaba.modules.vbmapp.repository.VBMappRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VBMappService {

    private final VBMappRepository vbMappRepository;

    private final VBMappColetaRepository vbMappColetaRepository;

    public VBMappService(VBMappRepository vbMappRepository, VBMappColetaRepository vbMappColetaRepository) {
        this.vbMappRepository = vbMappRepository;
        this.vbMappColetaRepository = vbMappColetaRepository;
    }

    public VbMappAvaliacao saveAvaliacao(VbMappAvaliacao vbMappAvaliacao) {
        return vbMappRepository.save(vbMappAvaliacao);
    }

    public VbMappAvaliacao findById(UUID vbmappId) {
        return vbMappRepository.findById(vbmappId).orElseThrow(() -> new RuntimeException("Não foi possível localizar a avaliação."));
    }

    public void saveColetaAvaliacao(List<VbMappColeta> vbMappColeta) {

        List<Integer> coletasIds = vbMappColeta.stream().map(i -> i.getColetaId()).toList();

        List<VbMappColeta> coletasRespondidas = vbMappColetaRepository.findAllColetasRespondidas(coletasIds);

        if(!coletasRespondidas.isEmpty()) {
            coletasRespondidas.forEach(i -> vbMappColetaRepository.deleteByVbmappColetaId(i.getVbmappColetaId()));
        }

        vbMappColetaRepository.saveAll(vbMappColeta);
    }

    public List<VbMappAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return vbMappRepository.findAllByAprendiz_aprendizId(aprendizId);
    }
}
