package com.zupacademy.eduardo.meli.cliente;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @Email
    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        String senhaEncriptada = encriptarSenha();
        return new Usuario(login, senhaEncriptada);
    }

    private String encriptarSenha() {
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
