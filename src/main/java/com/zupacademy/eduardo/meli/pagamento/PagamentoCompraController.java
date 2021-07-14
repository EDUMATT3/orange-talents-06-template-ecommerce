package com.zupacademy.eduardo.meli.pagamento;

import com.zupacademy.eduardo.meli.compra.Compra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PagamentoCompraController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EventosCompraSucesso eventosCompraSucesso;

    @PostMapping("pag-seguro/{idCompra}")
    @Transactional
    public ResponseEntity<?> retornoPagseguro(@PathVariable Long idCompra, @Valid PagamentoPagseguroRequest request){
        return processa(idCompra, request);
    }

    @PostMapping("paypal/{idCompra}")
    @Transactional
    public ResponseEntity<?> retornoPaypal(@PathVariable Long idCompra, @Valid PagamentoPaypalRequest request){
       return processa(idCompra, request);
    }

    private ResponseEntity<?> processa(Long idCompra, PagamentoRetornoRequest request) {
        Optional<Compra> possivelCompra = Optional.ofNullable(em.find(Compra.class, idCompra));

        if(possivelCompra.isEmpty()) return ResponseEntity.notFound().build();

        Compra compra = possivelCompra.get();
        compra.adicionarTransacao(request);

        em.merge(compra);

        eventosCompraSucesso.processa(compra);

        return ResponseEntity.ok().build();
    }
}
