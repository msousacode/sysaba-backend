package br.com.sysaba.modules.usuario.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("banco_demonstracao")
    private String bancoDemonstracao;

    public UsuarioDTO(String fullName, String email, String bancoDemonstracao) {
        this.fullName = fullName;
        this.email = email;
        this.bancoDemonstracao = bancoDemonstracao;
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

    public String getBancoDemonstracao() {
        return bancoDemonstracao;
    }

    public void setBancoDemonstracao(String bancoDemonstracao) {
        this.bancoDemonstracao = bancoDemonstracao;
    }
}
