package com.zupacademy.eduardo.meli.compartilhado;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ExistsValueValidator.class})
@Target({ FIELD})
@Retention(RUNTIME)
public @interface ExistsValue {

    String message() default "{beanvalidation.existsvalue}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String fieldName() default "id";
    Class<?> domainClass();
}