package br.com.sysaba.modules.usuario.dtos;

import br.com.sysaba.modules.cargo.CargoDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UsuarioDTO {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;

    private String perfil;

    @JsonProperty("cargo")
    private UUID cargoId;

    private String cargoDescricao;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String fullName, String email, String senha, String perfil, UUID cargoId, String cargoDescricao) {
        this.fullName = fullName;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.cargoId = cargoId;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public UUID getCargoId() {
        return cargoId;
    }

    public void setCargoId(UUID cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoDescricao() {
        return cargoDescricao;
    }

    public void setCargoDescricao(String cargoDescricao) {
        this.cargoDescricao = cargoDescricao;
    }
}
