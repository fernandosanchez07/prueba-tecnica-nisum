package co.com.nisum.usuarios.domain.requester;


import co.com.nisum.usuarios.infraestructure.entrypoints.annotations.PatternPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicioSesionRequest {

    @NotBlank(message = "El campo no puede estar vacio.")
    @Email(message = "El campo es de tipo email.")
    private String email;
    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    @PatternPassword
    private String password;
}
