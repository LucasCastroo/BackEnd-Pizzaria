package br.com.unitins.a1.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TelefoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Telefone {
    String message() default "Número inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
