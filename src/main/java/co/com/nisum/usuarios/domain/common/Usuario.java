package co.com.nisum.usuarios.domain.common;

import jakarta.persistence.Access;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private UUID id;
    private String nombre;
    private String email;
    private String password;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
