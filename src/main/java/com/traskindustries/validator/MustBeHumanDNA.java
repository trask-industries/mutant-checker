package com.traskindustries.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = HumanDNAValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MustBeHumanDNA {
    String message() default "The DNA is not human.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
