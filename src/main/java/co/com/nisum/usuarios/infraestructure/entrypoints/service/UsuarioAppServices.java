package co.com.nisum.usuarios.infraestructure.entrypoints.service;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAppServices {

    private final UsuarioPort usuarioPort;

    public UsuarioResponse registrarUsuario(UsuarioRequest request) {
        return this.usuarioPort.registrarUsuario(request);
    }
}
