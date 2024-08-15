package co.com.nisum.usuarios.domain.requester;

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

    private String name;
    private String email;
    private String password;
    private List<TelefonoRequest> phones;
}
