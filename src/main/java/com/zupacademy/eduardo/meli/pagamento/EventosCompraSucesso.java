package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;
import com.zupacademy.eduardo.meli.pergunta.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosCompraSucesso {
    @Autowired
    private Set<EventoCompraSucesso> eventosCompraSucesso;

    @Autowired
    private EmailService emailService;

    public void processa(Compra compra){
        if(compra.temTransacaoBemSucedida()) {
            eventosCompraSucesso.forEach(e -> e.processa(compra));
            //o envio do email pode ser feito em uma implementação de EventoCompraSucesso
            emailService.enviaEmailPagamentoSucesso(compra);
        }else {
            //o envio do email pode ser feito em uma implementação de EventoCompraErro
            emailService.enviaEmailPagamentoSucesso(compra);
        }
    }
}
