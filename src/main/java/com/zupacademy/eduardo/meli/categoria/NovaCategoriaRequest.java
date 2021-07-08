package com.zupacademy.eduardo.meli.categoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zupacademy.eduardo.meli.compartilhado.ExistsValue;
import com.zupacademy.eduardo.meli.compartilhado.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class NovaCategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistsValue(domainClass = Categoria.class)
    private Long categoriaMaeId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaCategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public void setCategoriaMaeId(Long categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }

    public Categoria toModel(EntityManager em) {
        Categoria novaCategoria = new Categoria(this.nome);

        if(Objects.nonNull(categoriaMaeId)){
            Categoria categoriaMae = em.find(Categoria.class, this.categoriaMaeId);
            Assert.state(Objects.nonNull(categoriaMae), "Categoria não encontrada com o id: "+ categoriaMaeId);
            novaCategoria.setCategoriaMae(categoriaMae);
        }

        return novaCategoria;
    }
}
