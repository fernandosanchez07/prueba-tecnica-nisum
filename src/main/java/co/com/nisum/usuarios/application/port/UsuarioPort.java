package co.com.nisum.usuarios.application.port;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.UsuarioActualizacionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioPort {

    UserDetails loadUserByUsername(String username);

    UsuarioActualizacionResponse actualizarUsuario(UsuarioActualizacionRequest request) throws HandledException;
}
