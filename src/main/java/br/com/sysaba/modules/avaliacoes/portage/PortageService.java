package br.com.sysaba.modules.avaliacoes.portage;

import br.com.sysaba.modules.avaliacoes.portage.repository.PortageColetaRepository;
import br.com.sysaba.modules.avaliacoes.portage.repository.PortageRepository;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappColeta;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void saveColetaAvaliacao(List<PortageColeta> portageColetaList) {

        List<Integer> coletasIds = portageColetaList.stream().map(i -> i.getColetaId()).toList();

        List<PortageColeta> coletasRespondidas = portageColetaRepository.findAllColetasRespondidas(coletasIds);

        if (!coletasRespondidas.isEmpty()) {
            coletasRespondidas.forEach(i -> portageColetaRepository.deleteByVbmappColetaId(i.getPortageColetaId()));
        }

        portageColetaRepository.saveAll(portageColetaList);
    }

    public PortageAvaliacao findById(UUID vbmappId) {
        return portageRepository.findById(vbmappId).orElseThrow(() -> new RuntimeException("Não foi possível localizar a avaliação."));
    }

    public List<PortageAvaliacao> findAllByAprendizId(UUID aprendizId) {
        return portageRepository.findAllByAprendiz_aprendizId(aprendizId);
    }

    public List<PortageColeta> findByColetasRespondidas(UUID vbmappUuid) {
        return portageColetaRepository.findByPortage_portageId(vbmappUuid);
    }
}
