package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAdapter {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenPort jwtTokenPort;

    private final AuthenticationManager authenticationManager;

    public void iniciarSesion(UsuarioRegistroRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario usuario = this.usuarioRepository.consultarPorEmail(request.getEmail());
        String token = jwtTokenPort.generarToken(usuario);

    }
}
