package com.zupacademy.eduardo.meli.pergunta;

import com.zupacademy.eduardo.meli.produto.Produto;
import com.zupacademy.eduardo.meli.usuario.Usuario;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class NovaPerguntaController {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EmailService emailService;

    @PostMapping("produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> adiciona(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request){

        Optional<Produto> possivelProduto = Optional.ofNullable(em.find(Produto.class, id));

        if(possivelProduto.isEmpty())
            return ResponseEntity.notFound().build();

        Usuario usuario = em.find(Usuario.class, 1L);
        Pergunta novaPergunta = request.toModel(possivelProduto.get(), usuario);
        em.persist(novaPergunta);
        emailService.enviaPergunta(novaPergunta);

        return ResponseEntity.ok().build();
    }

}
