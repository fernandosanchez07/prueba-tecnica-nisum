package co.com.nisum.usuarios.application.port;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.InicioSesionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.InicioSesionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;

public interface AutenticacionPort {

    UsuarioRegistroResponse registrarUsuario(UsuarioRegistroRequest request) throws HandledException;

    InicioSesionResponse iniciarSesion(InicioSesionRequest request);
}
