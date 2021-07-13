package com.zupacademy.eduardo.meli.pergunta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaPerguntaRequest(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, produto, usuario);
    }
}
