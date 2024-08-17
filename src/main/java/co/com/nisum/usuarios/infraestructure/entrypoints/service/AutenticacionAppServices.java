package co.com.nisum.usuarios.infraestructure.entrypoints.service;

import co.com.nisum.usuarios.application.port.AutenticacionPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacionAppServices {
    private final AutenticacionPort autenticacionPort;

    public UsuarioRegistroResponse registrarUsuario(UsuarioRegistroRequest request) throws HandledException {
        return this.autenticacionPort.registrarUsuario(request);
    }
}
