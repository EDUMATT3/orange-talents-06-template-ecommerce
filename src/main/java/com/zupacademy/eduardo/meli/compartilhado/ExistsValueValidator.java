package com.zupacademy.eduardo.meli.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsValueValidator implements ConstraintValidator<ExistsValue, Long>{

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(ExistsValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {

        if(value==null) return true;

        List<?> list = manager.createQuery("select 1 from "+klass.getName()+" where "+domainAttribute+"=:value")
                .setParameter("value", value)
                .getResultList();;

        Assert.isTrue(list.size() <=1, "aconteceu algo bizarro e você tem mais de um "+klass+" com o atributo "+domainAttribute+" com o valor = "+value);

        return !list.isEmpty();
    }
}