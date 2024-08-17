package co.com.nisum.usuarios.domain.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioActualizacionResponse {

    private LocalDateTime modified;
}
