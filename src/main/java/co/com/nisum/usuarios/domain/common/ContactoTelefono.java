package co.com.nisum.usuarios.domain.common;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactoTelefono {

    private UUID id;
    @Column(name = "id_user")
    private UUID idUser;
    private String numberPhone;
    private String cityCode;
    private String countryCode;
    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;
    private LocalDateTime fechaActualizacion;
    private String usuarioActualizacion;
}
