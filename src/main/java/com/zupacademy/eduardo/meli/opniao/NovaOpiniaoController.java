package com.zupacademy.eduardo.meli.opniao;

import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class NovaOpiniaoController {

    @PersistenceContext
    private EntityManager em;

    @PostMapping("produtos/{id}/opinioes")
    @Transactional
    public ResponseEntity<?> novaOpniao(@PathVariable Long id, @RequestBody @Valid NovaOpiniaoRequest request, @AuthenticationPrincipal Authentication authentication){


        Optional<Produto> possivelProduto = Optional.ofNullable(em.find(Produto.class, id));

        if(possivelProduto.isEmpty())
            return ResponseEntity.notFound().build();

        Usuario usuario = em.find(Usuario.class, 1L);
        Opiniao opiniao = request.toModel(possivelProduto.get(), usuario);

        em.persist(opiniao);

        return ResponseEntity.ok().build();
    }
}
