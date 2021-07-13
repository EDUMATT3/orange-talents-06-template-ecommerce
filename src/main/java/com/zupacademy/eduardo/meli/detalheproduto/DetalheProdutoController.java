package com.zupacademy.eduardo.meli.detalheproduto;

import com.zupacademy.eduardo.meli.produto.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class DetalheProdutoController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("{id}")
    public ResponseEntity<?> detalhes(@PathVariable Long id){
        Optional<Produto> possivelProduto = Optional.ofNullable(em.find(Produto.class, id));

        if(possivelProduto.isEmpty())
            return ResponseEntity.notFound().build();

        DetalheProdutoResponse response = new DetalheProdutoResponse(possivelProduto.get());
        return ResponseEntity.ok(response);
    }

}
