package com.zupacademy.eduardo.meli.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value == null) return true;

        List<?> list = entityManager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value")
                .setParameter("value", value)
                .getResultList();

        Assert.state(list.size() <= 1, "Foi encontrado mais de um " + klass.getName() + " com o atributo " + domainAttribute + " = " + value);

        return list.isEmpty();
    }
}
