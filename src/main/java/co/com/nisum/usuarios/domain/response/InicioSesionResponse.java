package co.com.nisum.usuarios.domain.response;


import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InicioSesionResponse {

    private UUID id;
    private String token;
}
