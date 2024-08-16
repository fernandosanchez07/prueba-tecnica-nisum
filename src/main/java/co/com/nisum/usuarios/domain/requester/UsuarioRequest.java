package co.com.nisum.usuarios.domain.requester;

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
public class UsuarioRequest {

    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    private String name;
    private String email;
    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    private String password;
    private List<TelefonoRequest> phones;
}
