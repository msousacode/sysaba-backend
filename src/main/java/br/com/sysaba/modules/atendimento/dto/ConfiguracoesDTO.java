package br.com.sysaba.modules.atendimento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ConfiguracoesDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonProperty("data_final")
    private LocalDate dataFinal;

    private Integer repetir;

    private Boolean seg;

    private Boolean ter;

    private Boolean qua;

    private Boolean qui;

    private Boolean sex;

    private Boolean sab;

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Integer getRepetir() {
        return repetir;
    }

    public void setRepetir(Integer repetir) {
        this.repetir = repetir;
    }

    public Boolean getSeg() {
        return seg;
    }

    public void setSeg(Boolean seg) {
        this.seg = seg;
    }

    public Boolean getTer() {
        return ter;
    }

    public void setTer(Boolean ter) {
        this.ter = ter;
    }

    public Boolean getQua() {
        return qua;
    }

    public void setQua(Boolean qua) {
        this.qua = qua;
    }

    public Boolean getQui() {
        return qui;
    }

    public void setQui(Boolean qui) {
        this.qui = qui;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public Boolean getSab() {
        return sab;
    }

    public void setSab(Boolean sab) {
        this.sab = sab;
    }
}
