package co.com.nisum.usuarios.domain.requester;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActualizacionRequest extends UsuarioRegistroRequest {

    @NotNull(message = "El campo no puede ser nulo.")
    private UUID id;

}
