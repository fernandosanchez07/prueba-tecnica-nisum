package co.com.nisum.usuarios.domain.requester;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActualizacionRequest {

    @NotNull(message = "El campo no puede ser nulo.")
    private UUID id;

    @NotNull(message = "El campo no puede ser nulo.")
    @NotBlank(message = "El campo no puede estar vacio.")
    private String name;
}
