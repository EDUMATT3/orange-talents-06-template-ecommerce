package com.zupacademy.eduardo.meli.compra;

import com.zupacademy.eduardo.meli.compartilhado.ExistsValue;
import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Objects;

public class NovaCompraRequest {

    @NotNull
    @ExistsValue(domainClass = Produto.class)
    private Long produtoId;
    @NotNull
    @Positive
    private int quantidade;
    @NotNull
    private GatewayPagemento pagamento;

    public NovaCompraRequest(Long produtoId, int quantidade, @NotNull GatewayPagemento pagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.pagamento = pagamento;
    }

    public Long getProdutoId() {
        return this.produtoId;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public GatewayPagemento getGateway() {
        return pagamento;
    }

    public Compra toModel(EntityManager em, Usuario usuario) {
        Produto produto = em.find(Produto.class, this.produtoId);
        Assert.state(Objects.nonNull(produto), "Produto não deveria ser nulo");

        produto.abaterEstoque(this.quantidade);

        return new Compra(quantidade, pagamento, produto, usuario);
    }
}
