package co.com.nisum.usuarios.infraestructure.entrypoints.service;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAppServices {

    private final UsuarioPort usuarioPort;

    public UsuarioResponse registrarUsuario(UsuarioRegistroRequest request) throws HandledException {
        return this.usuarioPort.registrarUsuario(request);
    }

    public UsuarioResponse actualizarUsuario(UsuarioActualizacionRequest request) throws HandledException {
        return this.usuarioPort.actualizarUsuario(request);
    }
}
