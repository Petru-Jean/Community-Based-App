package org.springprojects.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.*;

@NotNull @NotBlank @Length(min = 3, max = 21)
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameValidation
{
    String message() default "Not a valid Username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
