package co.com.nisum.usuarios.infraestructure.entrypoints.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ValidationPatternPassword {

    @Value("${validation.pattern-password}")
    private String pattern;


}
