package com.zupacademy.eduardo.meli.compra;

import com.zupacademy.eduardo.meli.pagamento.PagamentoPagseguroRequest;
import com.zupacademy.eduardo.meli.pagamento.PagamentoRetornoRequest;
import com.zupacademy.eduardo.meli.pagamento.Transacao;
import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {}

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

    public boolean temTransacaoBemSucedida(){
        return this.transacoes.stream().anyMatch(Transacao::sucesso);
    }

    public void adicionarTransacao(PagamentoRetornoRequest request){
        Transacao transacao = request.toTransacao(this);

        Assert.isTrue(!this.transacoes.contains(transacao), "Já existe uma transação com esse id");

        boolean transacaoSucesso = this.transacoes.stream().anyMatch(Transacao::sucesso);
        Assert.isTrue(!temTransacaoBemSucedida(), "Compra com transação completa já feita");

        this.transacoes.add(transacao);
    }

    public Long getIdComprador() {
        return this.comprador.getId();
    }

    public Long getIdVendedor() {
        return this.produto.getIdVendedor();
    }
}
