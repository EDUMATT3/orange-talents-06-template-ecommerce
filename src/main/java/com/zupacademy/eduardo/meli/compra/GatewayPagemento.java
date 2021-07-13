package com.zupacademy.eduardo.meli.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagemento {
    pagseguro{
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetorno = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return String.format("pagseguro.com/%s?redirectUrl=%s", compra.getId(), urlRetorno);
        }
    },
    paypal{
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetorno = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();

            return String.format("paypal.com/%s?redirectUrl=%s", compra.getId(), urlRetorno);
        }
    };

    abstract String criaUrlRetorno(Compra compra,
                                   UriComponentsBuilder uriComponentsBuilder);
}
