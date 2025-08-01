package br.com.sysaba.modules.profissional;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ProfissionalDTO {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    private String perfil;

    private UUID cargo;

    private String cargoDescricao;

    private String senha;

    public ProfissionalDTO() {
    }

    public ProfissionalDTO(String fullName, String email, String perfil, UUID cargo, String cargoDescricao) {
        this.fullName = fullName;
        this.email = email;
        this.perfil = perfil;
        this.cargo = cargo;
        this.cargoDescricao = cargoDescricao;    
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

    public UUID getCargo() {
        return cargo;
    }

    public void setCargo(UUID cargo) {
        this.cargo = cargo;
    }

    public String getCargoDescricao() {
        return cargoDescricao;
    }

    public void setCargoDescricao(String cargoDescricao) {
        this.cargoDescricao = cargoDescricao;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
