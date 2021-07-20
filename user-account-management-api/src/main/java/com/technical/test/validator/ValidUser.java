package com.technical.test.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidUser {
    String message() default "{constraints.valid.user}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
