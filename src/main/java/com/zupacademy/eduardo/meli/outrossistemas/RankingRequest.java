package com.zupacademy.eduardo.meli.outrossistemas;

import javax.validation.constraints.NotNull;

public class RankingRequest {

    @NotNull
    private String idCompra, idVendedor;

    public RankingRequest(String idCompra, String idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    public String getIdCompra() {
        return idCompra;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

}
