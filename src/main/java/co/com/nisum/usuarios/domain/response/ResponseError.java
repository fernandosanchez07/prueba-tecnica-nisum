package co.com.nisum.usuarios.domain.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseError {

    private String mensaje;
}
