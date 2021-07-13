package com.zupacademy.eduardo.meli.compra;

import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Map;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NotNull @Positive int quantidade;

    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario comprador;
    @NotNull
    private BigDecimal valorProduto;
    @NotNull
    @Enumerated
    private GatewayPagemento gateway;

    public Compra(@NotNull @Positive int quantidade, @NotNull GatewayPagemento gateway, Produto produto, Usuario comprador) {
        this.quantidade = quantidade;
        this.gateway = gateway;
        this.produto = produto;
        this.comprador = comprador;
        this.valorProduto = produto.getValor();
    }

    public String getEmailComprador() {
        return this.comprador.getUsername();
    }

    public String getEmailVendedor(){
        return this.produto.getNomeVendedor();
    }

    public Long getId() {
        return this.id;
    }

    public String urlRedirecionamento(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrlRetorno(this, uriComponentsBuilder);
    }
}
