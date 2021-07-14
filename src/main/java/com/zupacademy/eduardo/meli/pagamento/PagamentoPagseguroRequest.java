package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPagseguroRequest implements PagamentoRetornoRequest{

    @NotBlank
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public PagamentoPagseguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public Transacao toTransacao(Compra compra){
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
