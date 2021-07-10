package com.zupacademy.eduardo.meli.cliente;

import com.zupacademy.eduardo.meli.categoria.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @PersistenceContext
    EntityManager em;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid NovoUsuarioRequest request){
        Usuario novoUsuario = request.toModel();
        em.persist(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
