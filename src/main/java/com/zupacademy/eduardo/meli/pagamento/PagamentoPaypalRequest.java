package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoPaypalRequest implements PagamentoRetornoRequest {

    @NotBlank
    private String idTransacao;
    @NotNull
    @Range(min = 0, max = 1)
    private int status;

    public PagamentoPaypalRequest(@NotBlank String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Transacao toTransacao(Compra compra){
        StatusTransacao statusTransacao = status==0 ? StatusTransacao.erro : StatusTransacao.sucesso;
        return new Transacao(statusTransacao, idTransacao, compra);
    }

}
