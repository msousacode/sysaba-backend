package br.com.sysaba.modules.acesso.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioInfoPerfilDTO {

    private String email;

    private String documento;

    @JsonProperty(value = "full_name")
    private String nome;

    public UsuarioInfoPerfilDTO() {
    }

    public UsuarioInfoPerfilDTO(String email, String documento, String nome) {
        this.email = email;
        this.documento = documento;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
