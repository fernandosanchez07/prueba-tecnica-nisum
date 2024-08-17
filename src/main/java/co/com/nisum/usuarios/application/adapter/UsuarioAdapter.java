package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.response.UsuarioActualizacionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;


@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails obtenerUserDetails(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.consultarPorEmail(username);
        return new User(usuario.getEmail(), usuario.getPassword(), new ArrayList<>());
    }

    @Override
    public UsuarioActualizacionResponse actualizarUsuario(UsuarioActualizacionRequest request) throws HandledException {
        Usuario usuario = modificarUsuario(request);

        Usuario usuarioActualizado = this.usuarioRepository.actualizarUsuario(usuario);

        return UsuarioActualizacionResponse.builder()
                .modified(usuarioActualizado.getFechaActualizacion())
                .build();
    }

    private Usuario modificarUsuario(UsuarioActualizacionRequest request) throws HandledException {
        Usuario usuario = this.usuarioRepository.consultarPorId(request.getId());
        usuario.setNombre(request.getName());
        usuario.setFechaActualizacion(LocalDateTime.now());
        usuario.setUsuarioActualizacion("NISUM");

        return usuario;
    }

}
