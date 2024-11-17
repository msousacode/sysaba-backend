package br.com.sysaba.modules.atendimento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class AtendimentoDTO {

    private String uuid;

    private AprendizDTO aprendiz;

    @JsonProperty("data_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;

    private boolean sync;

    private List<TreinamentoItemDTO> treinamentos;

    private String aprendizUuidFk;

    // Getters e Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public AprendizDTO getAprendiz() {
        return aprendiz;
    }

    public void setAprendiz(AprendizDTO aprendiz) {
        this.aprendiz = aprendiz;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public List<TreinamentoItemDTO> getTreinamentos() {
        return treinamentos;
    }

    public void setTreinamentos(List<TreinamentoItemDTO> treinamentos) {
        this.treinamentos = treinamentos;
    }

    public String getAprendizUuidFk() {
        return aprendizUuidFk;
    }

    public void setAprendizUuidFk(String aprendizUuidFk) {
        this.aprendizUuidFk = aprendizUuidFk;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}

// DTO para o campo "aprendiz"
class AprendizDTO {
    private String label;
    private String value;

    // Getters e Setters
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

// DTO para os itens da lista "treinamentos"
class TreinamentoItemDTO {
    private String treinamento;
    private String protocolo;
    private boolean ativo;
    private ConfiguracoesDTO configuracoes;

    // Getters e Setters
    public String getTreinamento() {
        return treinamento;
    }

    public void setTreinamento(String treinamento) {
        this.treinamento = treinamento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public ConfiguracoesDTO getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ConfiguracoesDTO configuracoes) {
        this.configuracoes = configuracoes;
    }
}

// DTO para o campo "configuracoes" dentro de "treinamentos"
class ConfiguracoesDTO {
    private String dataFinal;
    private int repetir;
    private boolean seg;
    private boolean ter;
    private boolean qua;
    private boolean qui;
    private boolean sex;
    private boolean sab;

    // Getters e Setters
    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getRepetir() {
        return repetir;
    }

    public void setRepetir(int repetir) {
        this.repetir = repetir;
    }

    public boolean isSeg() {
        return seg;
    }

    public void setSeg(boolean seg) {
        this.seg = seg;
    }

    public boolean isTer() {
        return ter;
    }

    public void setTer(boolean ter) {
        this.ter = ter;
    }

    public boolean isQua() {
        return qua;
    }

    public void setQua(boolean qua) {
        this.qua = qua;
    }

    public boolean isQui() {
        return qui;
    }

    public void setQui(boolean qui) {
        this.qui = qui;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean isSab() {
        return sab;
    }

    public void setSab(boolean sab) {
        this.sab = sab;
    }
}
