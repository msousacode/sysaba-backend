package br.com.sysaba.modules.atendimento.dto;

public class AprendizDTO {
    private String label;
    private String value;

    public AprendizDTO() {
    }

    public AprendizDTO(String label, String value) {
        this.label = label;
        this.value = value;
    }

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
