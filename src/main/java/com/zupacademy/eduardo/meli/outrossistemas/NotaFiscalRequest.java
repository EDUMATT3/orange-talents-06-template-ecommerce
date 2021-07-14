package com.zupacademy.eduardo.meli.outrossistemas;

import javax.validation.constraints.NotNull;

public class NotaFiscalRequest {

    @NotNull
    private String idCompra, idComprador;

    public NotaFiscalRequest(String idCompra, String idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public String getIdComprador() {
        return idComprador;
    }
}
