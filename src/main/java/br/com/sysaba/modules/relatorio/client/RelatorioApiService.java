package br.com.sysaba.modules.relatorio.client;

import br.com.sysaba.modules.relatorio.RelatorioService;
import br.com.sysaba.modules.relatorio.dto.RelatorioDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RelatorioApiService {

    private final RestTemplate restTemplate;

    public RelatorioApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void postRelatorioTreinamentos(RelatorioDTO dto) {
        try {
            String url = "http://localhost:3000/print";
            restTemplate.postForObject(url, dto, String.class);
        } catch (RuntimeException ex) {
            throw new RuntimeException(RelatorioService.class.getName(), ex);
        }
    }
}
