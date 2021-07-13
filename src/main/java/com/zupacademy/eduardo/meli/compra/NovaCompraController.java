package com.zupacademy.eduardo.meli.compra;

import com.zupacademy.eduardo.meli.pergunta.EmailService;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("compras")
public class NovaCompraController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private EmailService emailService;

    @InitBinder
    public void init(WebDataBinder dataBinder){
        dataBinder.addValidators(new QuantidadeEstoqueValidator(em));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> finalizar(@RequestBody @Valid NovaCompraRequest request, UriComponentsBuilder uriBuilder, @AuthenticationPrincipal Usuario usuario){

        Compra novaCompra = request.toModel(em, usuario);
        em.persist(novaCompra);

        emailService.enviaEmailCompra(novaCompra);

        String uri = novaCompra.urlRedirecionamento(uriBuilder);

        return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION,uri).build();
    }

}
