package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.mensajes.MensajePersonalizado;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse registrarUsuario(UsuarioRequest request) throws HandledException {
        if(this.usuarioRepository.existeCorreo(request.getEmail())){
            throw new HandledException(MensajePersonalizado.obtenerMensaje("mensaje.error.register-email-exist"));
        }
        return null;
    }
}
