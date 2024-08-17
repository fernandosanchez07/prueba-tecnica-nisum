package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.AutenticacionPort;
import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.common.ContactoTelefono;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.mensajes.MensajePersonalizado;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.InicioSesionRequest;
import co.com.nisum.usuarios.domain.requester.TelefonoRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.InicioSesionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class AutenticacionAdapter implements AutenticacionPort {

    private final UsuarioRepository usuarioRepository;
    private final ContactoTelefonoRepository contactoTelefonoRepository;
    private final JwtTokenPort jwtTokenPort;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UsuarioRegistroResponse registrarUsuario(UsuarioRegistroRequest request) throws HandledException {

        validarSiExisteCorreo(request.getEmail());

        Usuario usuario = this.usuarioRepository.registrarUsuario(
                Usuario.builder()
                        .nombre(request.getName())
                        .email(request.getEmail())
                        .password(this.passwordEncoder.encode(request.getPassword()))
                        .build()
        );

        this.contactoTelefonoRepository.guardarTelefonos(
                crearListaTelefonos(request.getPhones(), usuario)
        );

        return UsuarioRegistroResponse.builder()
                .id(usuario.getId())
                .created(usuario.getFechaCreacion())
                .lastLogin(usuario.getFechaCreacion())
                .token(this.jwtTokenPort.generarToken(usuario))
                .isActive(Boolean.TRUE)
                .build();
    }

    private void validarSiExisteCorreo(String email) throws HandledException {
        if (this.usuarioRepository.existeCorreo(email)) {
            throw new HandledException(MensajePersonalizado.obtenerMensaje("mensaje.error.register-email-exist"));
        }
    }

    private List<ContactoTelefono> crearListaTelefonos(List<TelefonoRequest> phones, Usuario usuario) {
        return phones.stream()
                .map(phone ->
                        ContactoTelefono.builder()
                                .numberPhone(phone.getNumber())
                                .cityCode(phone.getCityCode())
                                .countryCode(phone.getCityCode())
                                .idUser(usuario.getId())
                                .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public InicioSesionResponse iniciarSesion(InicioSesionRequest request) throws HandledException {

        validarExistenciaUsuario(request.getEmail());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario usuario = this.usuarioRepository.consultarPorEmail(request.getEmail());
        String token = this.jwtTokenPort.generarToken(usuario);

        return InicioSesionResponse.builder()
                .id(usuario.getId())
                .token(token)
                .build();
    }

    private void validarExistenciaUsuario(String email) throws HandledException {
        if (!this.usuarioRepository.existeCorreo(email)) {
            throw new HandledException(MensajePersonalizado.obtenerMensaje("mensaje.error.user-not-existe-login"));
        }
    }
}
