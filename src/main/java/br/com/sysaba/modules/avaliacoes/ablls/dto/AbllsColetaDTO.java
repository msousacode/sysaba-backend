package br.com.sysaba.modules.avaliacoes.ablls.dto;

import br.com.sysaba.modules.avaliacoes.ablls.AbllsColeta;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AbllsColetaDTO {

    @JsonProperty("aprendiz_uuid_fk")
    private String aprendizUuidFk;

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("descricao")
    private String descricao;

    @JsonProperty("id")
    private int id;

    @JsonProperty("resposta")
    private int resposta;

    @JsonProperty("criado_por_nome")
    private String criadoNome;

    public static AbllsColetaDTO of(AbllsColeta abllsColeta) {
        AbllsColetaDTO ablls = new AbllsColetaDTO();
        ablls.setId(abllsColeta.getColetaId());
        ablls.setAprendizUuidFk(String.valueOf(abllsColeta.getAprendiz().getAprendizId()));
        ablls.setCodigo(abllsColeta.getCodigo());
        ablls.setResposta(abllsColeta.getResposta());
        ablls.setCriadoNome(abllsColeta.getCriadoNome());
        ablls.setDescricao(abllsColeta.getDescricao());
        return ablls;
    }

    // Getters e Setters

    public String getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(String aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public String getCriadoNome() {
        return criadoNome;
    }

    public void setCriadoNome(String criadoNome) {
        this.criadoNome = criadoNome;
    }
}
