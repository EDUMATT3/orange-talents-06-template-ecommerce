package com.zupacademy.eduardo.meli.cliente;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticacaoRequest {
    private String login, senha;

    public AutenticacaoRequest() {}

    public UsernamePasswordAuthenticationToken build() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
