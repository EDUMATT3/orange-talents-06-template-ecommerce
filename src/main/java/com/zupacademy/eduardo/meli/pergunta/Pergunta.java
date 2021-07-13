package com.zupacademy.eduardo.meli.pergunta;

import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @ManyToOne
    private Usuario interessado;
    private LocalDateTime createdAt;

    @Deprecated
    public Pergunta(){}

    public Pergunta(@NotBlank String titulo, Produto produto, Usuario interessado) {
        this.titulo = titulo;
        this.produto = produto;
        this.interessado = interessado;
        this.createdAt = LocalDateTime.now();
    }

    public String getEmailInteressado() {
        return this.interessado.getUsername();
    }

    public String getEmailVendedor() {
        return this.produto.getNomeVendedor();
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}
