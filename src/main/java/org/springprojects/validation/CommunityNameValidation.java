package org.springprojects.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.lang.annotation.*;

@NotNull
@NotBlank
@Length(min = 3, max = 21)
@Pattern(regexp = "^[A-Za-z0-9_.-]+$")

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CommunityNameValidation
{
    String message() default "Not a valid Community name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
