package co.com.nisum.usuarios.domain.requester;

import co.com.nisum.usuarios.infraestructure.entrypoints.annotations.PatternPassword;
import co.com.nisum.usuarios.infraestructure.entrypoints.component.ValidationPatternPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRegistroRequest {

    private static String patternPassword;

    public void setPatternPassword(ValidationPatternPassword patternPassword) {
        UsuarioRegistroRequest.patternPassword = patternPassword.getPattern();
    }

    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    private String name;
    @NotBlank(message = "El campo no puede estar vacio.")
    @Email(message = "El campo es de tipo email.")
    private String email;
    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    @PatternPassword
    private String password;
    private List<TelefonoRequest> phones;
}
