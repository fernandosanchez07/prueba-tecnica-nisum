package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.common.ContactoTelefono;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.mensajes.MensajePersonalizado;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.TelefonoRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UsuarioAdapter implements UsuarioPort {

    private final UsuarioRepository usuarioRepository;
    private final ContactoTelefonoRepository contactoTelefonoRepository;

    @Override
    public UsuarioResponse registrarUsuario(UsuarioRequest request) throws HandledException {

        validarSiExisteCorreo(request);

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

    private void validarSiExisteCorreo(UsuarioRequest request) throws HandledException {
        if (this.usuarioRepository.existeCorreo(request.getEmail())) {
            throw new HandledException(MensajePersonalizado.obtenerMensaje("mensaje.error.register-email-exist"));
        }
    }
}
