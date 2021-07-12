package com.zupacademy.eduardo.meli.opniao;

import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NovaOpiniaoRequest {

    @NotNull
    @Range(min = 1, max = 5)
    private int nota;

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(@NotNull @Size(min = 1, max = 5) int nota, @NotBlank String titulo,
                              @NotBlank @Length(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, Usuario usuario) {
        return new Opiniao(nota, titulo, descricao, produto, usuario);
    }
}
