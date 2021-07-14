package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Enumerated
    private StatusTransacao status;
    @NotBlank
    private String idTransacao;
    @Valid
    @ManyToOne
    private Compra compra;
    @NotNull
    private LocalDateTime createdAt;

    @Deprecated
    public Transacao() {
    }

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao, @Valid Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.compra = compra;
        this.createdAt = LocalDateTime.now();
    }

    public boolean sucesso() {
        return this.status.equals(StatusTransacao.sucesso);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transacao transacao = (Transacao) o;

        return idTransacao != null ? idTransacao.equals(transacao.idTransacao) : transacao.idTransacao == null;
    }

    @Override
    public int hashCode() {
        return idTransacao != null ? idTransacao.hashCode() : 0;
    }
}
