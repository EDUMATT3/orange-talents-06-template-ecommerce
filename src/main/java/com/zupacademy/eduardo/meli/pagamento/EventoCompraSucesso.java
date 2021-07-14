package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;

public interface EventoCompraSucesso {
    void processa(Compra compra);
}
