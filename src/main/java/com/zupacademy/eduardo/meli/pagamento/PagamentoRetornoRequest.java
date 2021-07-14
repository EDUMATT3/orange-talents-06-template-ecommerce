package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;

public interface PagamentoRetornoRequest {
    Transacao toTransacao(Compra compra);
}
