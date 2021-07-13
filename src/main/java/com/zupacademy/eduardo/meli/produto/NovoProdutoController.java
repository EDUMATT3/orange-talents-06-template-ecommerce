package com.zupacademy.eduardo.meli.produto;

import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("produtos")
public class NovoProdutoController {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ImagemUploaderFakeImpl uploaderFake;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastro(@RequestBody @Valid NovoProdutoRequest request, @AuthenticationPrincipal Usuario authentication, @AuthenticationPrincipal Usuario usuario){
        Produto novoProduto = request.toModel(em, usuario);
        em.persist(novoProduto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("{id}/imagens")
    @Transactional
    public ResponseEntity<?> addImagens(@PathVariable Long id, @Valid NovasImagensRequest request, @AuthenticationPrincipal Usuario usuario){
        Optional<Produto> possivelProduto = Optional.ofNullable(em.find(Produto.class, id));

        if(possivelProduto.isEmpty())
            return ResponseEntity.notFound().build();

        Produto produto = possivelProduto.get();

        if(!produto.pertenceAoUsuario(usuario))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Set<String> links = uploaderFake.envia(request.getImagens());

        produto.adicionarImagens(links);

        em.merge(produto);

        return ResponseEntity.ok().build();
    }
}
