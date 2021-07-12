package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.cliente.UserService;
import com.zupacademy.eduardo.meli.cliente.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("produtos")
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario authentication){

        Produto novoProduto = request.toModel(em, em.find(Usuario.class, 1L));
        em.persist(novoProduto);
        return ResponseEntity.ok().build();
    }
}
