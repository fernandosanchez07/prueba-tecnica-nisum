package co.com.nisum.usuarios.application.port;

import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;

public interface UsuarioPort {

    UsuarioResponse registrarUsuario(UsuarioRequest request);
}
