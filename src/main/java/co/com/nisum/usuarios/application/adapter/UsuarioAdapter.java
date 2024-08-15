package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse registrarUsuario(UsuarioRequest request) {
        return null;
    }
}
