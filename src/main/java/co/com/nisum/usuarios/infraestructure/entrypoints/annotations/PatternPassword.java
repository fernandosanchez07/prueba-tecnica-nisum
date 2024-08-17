package co.com.nisum.usuarios.infraestructure.entrypoints.annotations;

import co.com.nisum.usuarios.infraestructure.entrypoints.component.CustomPatternValidatorPassword;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CustomPatternValidatorPassword.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatternPassword {
    String message() default "La contraseña no cumple con las condiciones requeridas.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
