package com.zupacademy.eduardo.meli.usuario;

import com.zupacademy.eduardo.meli.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, new SenhaManager(this.senha));
    }
}
