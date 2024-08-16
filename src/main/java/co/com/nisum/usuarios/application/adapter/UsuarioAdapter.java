package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.common.ContactoTelefono;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.mensajes.MensajePersonalizado;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.TelefonoRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;
    private final ContactoTelefonoRepository contactoTelefonoRepository;

    @Override
    public UsuarioResponse registrarUsuario(UsuarioRegistroRequest request) throws HandledException {

        validarSiExisteCorreo(request.getEmail());

        Usuario usuario = this.usuarioRepository.registrarUsuario(
                Usuario.builder()
                        .nombre(request.getName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        this.contactoTelefonoRepository.guardarTelefonos(
                crearListaTelefonos(request.getPhones(), usuario)
        );

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .created(usuario.getFechaCreacion())
                .lastLogin(usuario.getFechaCreacion())
                .token(null)
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
    public UsuarioResponse actualizarUsuario(UsuarioActualizacionRequest request) throws HandledException {
        Usuario usuario = modificarInformacion(request);

        Usuario usuarioActualizado = this.usuarioRepository.actualizarUsuario(usuario);

        return UsuarioResponse.builder()
                .id(usuarioActualizado.getId())
                .created(usuarioActualizado.getFechaCreacion())
                .modified(usuarioActualizado.getFechaActualizacion())
                .build();
    }

    private Usuario modificarInformacion(UsuarioActualizacionRequest request) throws HandledException {
        Usuario usuario = this.usuarioRepository.consultarPorId(request.getId());

        usuario.setNombre(request.getName());
        usuario.setEmail(verificarCorreo(request.getEmail(), usuario));
        usuario.setPassword(request.getPassword());
        usuario.setFechaActualizacion(LocalDateTime.now());
        usuario.setUsuarioActualizacion("NISUM");

        return usuario;
    }

    private String verificarCorreo(String email, Usuario usuario) throws HandledException {

        if (usuario.getEmail().equals(email)) {
            return email;
        }

        validarSiExisteCorreo(email);
        return email;
    }
}
