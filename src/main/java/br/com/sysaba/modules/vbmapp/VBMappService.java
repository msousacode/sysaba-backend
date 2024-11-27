package br.com.sysaba.modules.vbmapp;

import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
