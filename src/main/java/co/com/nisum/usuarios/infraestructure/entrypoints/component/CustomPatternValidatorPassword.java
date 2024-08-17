package co.com.nisum.usuarios.infraestructure.entrypoints.component;

import co.com.nisum.usuarios.infraestructure.entrypoints.annotations.PatternPassword;
import co.com.nisum.usuarios.infraestructure.entrypoints.component.ValidationPatternPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomPatternValidatorPassword implements ConstraintValidator<PatternPassword, String> {

    private final ValidationPatternPassword validationPatternPassword;

    public CustomPatternValidatorPassword(ValidationPatternPassword validationPatternPassword) {
        this.validationPatternPassword = validationPatternPassword;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String patternRegex = this.validationPatternPassword.getPattern();
        return Objects.isNull(value) ? true : value.matches(patternRegex);
    }
}
