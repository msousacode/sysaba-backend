package br.com.sysaba.modules.profissional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfissionalDTO {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    private String perfil;

    public ProfissionalDTO() {
    }

    public ProfissionalDTO(String fullName, String email, String perfil) {
        this.fullName = fullName;
        this.email = email;
        this.perfil = perfil;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
