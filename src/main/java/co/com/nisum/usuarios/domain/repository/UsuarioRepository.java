package co.com.nisum.usuarios.domain.repository;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;

public interface UsuarioRepository {

    Boolean existeCorreo(String email);

    Usuario registrarUsuario(Usuario usuario);
}
