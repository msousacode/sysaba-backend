package br.com.sysaba.modules.avaliacoes;

import br.com.sysaba.modules.avaliacoes.portage.PortageAvaliacao;
import br.com.sysaba.modules.avaliacoes.vbmapp.VbMappAvaliacao;

import java.util.UUID;

public class AvaliacaoDTO {

    private UUID id;
    private String tipo;
    private String protocolo;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(UUID id, String tipo, String protocolo) {
        this.id = id;
        this.tipo = tipo;
        this.protocolo = protocolo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public static AvaliacaoDTO of(VbMappAvaliacao v) {
        return new AvaliacaoDTO(v.getVbMappId(), v.getNiveisColeta(), v.getProtocolo());
    }

    public static AvaliacaoDTO of(PortageAvaliacao v) {
        return new AvaliacaoDTO(v.getPortageId(), v.getIdadesColeta(), v.getProtocolo());
    }
}
