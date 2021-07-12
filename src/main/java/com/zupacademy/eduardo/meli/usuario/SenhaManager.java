package com.zupacademy.eduardo.meli.usuario;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class SenhaManager {
    private String senha;

    public SenhaManager(@NotBlank @Length(min = 6) String senha) {
        Assert.hasLength(senha, "senha não pode ser em branco");
        Assert.isTrue(senha.length()>=6, "senha deve ter 6 caracteres no minimo");

        this.senha = senha;
    }

    public String enconde(){
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
