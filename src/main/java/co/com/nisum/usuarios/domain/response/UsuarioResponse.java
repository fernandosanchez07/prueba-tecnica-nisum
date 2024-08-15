package co.com.nisum.usuarios.domain.response;

import co.com.nisum.usuarios.domain.requester.TelefonoRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

}
