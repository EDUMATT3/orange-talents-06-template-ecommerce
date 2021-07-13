package com.zupacademy.eduardo.meli.compra;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

public class QuantidadeEstoqueValidator implements Validator {
    private EntityManager em;

    public QuantidadeEstoqueValidator(EntityManager em) {
        this.em = em;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return NovaCompraRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) return;

        NovaCompraRequest request = (NovaCompraRequest) o;

        int quantidadeEstoque = (int) em.createQuery("select p.quantidade from Produto p where p.id =?1")
                .setParameter(1, request.getProdutoId())
                .getSingleResult();

        if(quantidadeEstoque < request.getQuantidade())
            errors.rejectValue("quantidade", null, "Estoque insuficiente para essa quantidade");
    }
}
