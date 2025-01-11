package br.com.sysaba.modules.relatorio.client;

import br.com.sysaba.core.util.MapperUtil;
import br.com.sysaba.modules.relatorio.RelatorioService;
import br.com.sysaba.modules.relatorio.dto.LinkDowloadResponseDTO;
import br.com.sysaba.modules.relatorio.dto.RelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.barreiras.VBMappBarreiraRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.pei.PEIRelatorioDTO;
import br.com.sysaba.modules.relatorio.dto.portage.PortageRelatorioDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RelatorioApiService {

    private final RestTemplate restTemplate;

    public RelatorioApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${nestjs.relatorios.host}")
    private String relatorioHost;

    public LinkDowloadResponseDTO postRelatorioTreinamentos(RelatorioDTO dto) {
        try {
            String url = relatorioHost + "/print/relatorios/treinamentos";
            String responseObject = restTemplate.postForObject(url, dto, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LinkDowloadResponseDTO mapper = objectMapper.readValue(responseObject, LinkDowloadResponseDTO.class);

            return mapper;

        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkDowloadResponseDTO postRelatorioPortage(PortageRelatorioDTO dto) {
        try {
            String url = relatorioHost + "/print/relatorios/portage";
            String responseObject = restTemplate.postForObject(url, dto, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LinkDowloadResponseDTO mapper = objectMapper.readValue(responseObject, LinkDowloadResponseDTO.class);

            return mapper;

        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkDowloadResponseDTO postPEIRelatorioPortage(PEIRelatorioDTO dto) {
        try {
            String url = relatorioHost + "/print/relatorios/portage/pei";
            String responseObject = restTemplate.postForObject(url, dto, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LinkDowloadResponseDTO mapper = objectMapper.readValue(responseObject, LinkDowloadResponseDTO.class);

            return mapper;

        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkDowloadResponseDTO postPEIRelatorioVBMAPP(PEIRelatorioDTO dto) {
        try {
            String url = relatorioHost + "/print/relatorios/vbmapp/pei";
            String responseObject = restTemplate.postForObject(url, dto, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LinkDowloadResponseDTO mapper = objectMapper.readValue(responseObject, LinkDowloadResponseDTO.class);

            return mapper;

        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public LinkDowloadResponseDTO postVBBarreiraRelatorioVBMAPP(VBMappBarreiraRelatorioDTO dto) {
        try {
            String url = relatorioHost + "/print/relatorios/vbmapp/barreiras";
            String responseObject = restTemplate.postForObject(url, dto, String.class);

            ObjectMapper objectMapper = new ObjectMapper();
            LinkDowloadResponseDTO mapper = objectMapper.readValue(responseObject, LinkDowloadResponseDTO.class);

            return mapper;

        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
