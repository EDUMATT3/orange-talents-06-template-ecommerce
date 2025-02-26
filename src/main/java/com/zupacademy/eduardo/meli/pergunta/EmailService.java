package com.zupacademy.eduardo.meli.pergunta;

import com.zupacademy.eduardo.meli.compra.Compra;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmailService {

    @Autowired
    private Mailer mailer;

    public void enviaPergunta(Pergunta pergunta){
        Assert.state(Objects.nonNull(pergunta), "pergunta não deveria ser nula");

        mailer.send("any body", "pergunta 1",
                pergunta.getEmailInteressado(), "comunicados@meli.com", pergunta.getEmailVendedor());
    }

    public void enviaEmailCompra(Compra novaCompra) {
        Assert.state(Objects.nonNull(novaCompra), "compra não deveria ser nula");

        mailer.send("any body", "nova compra",
                novaCompra.getEmailComprador(), "comunicados@meli.com", novaCompra.getEmailVendedor());
    }

    public void enviaEmailPagamentoSucesso(Compra compra) {
        Assert.state(Objects.nonNull(compra), "compra não deveria ser nula");

        mailer.send("any body", "pagamento bem sucedido",
                compra.getEmailComprador(), "comunicados@meli.com", compra.getEmailVendedor());
    }

    public void enviaEmailPagamentoErro(Compra compra) {
        Assert.state(Objects.nonNull(compra), "compra não deveria ser nula");

        mailer.send("any body", "pagamento não efetuado",
                compra.getEmailComprador(), "comunicados@meli.com", compra.getEmailVendedor());
    }
}
