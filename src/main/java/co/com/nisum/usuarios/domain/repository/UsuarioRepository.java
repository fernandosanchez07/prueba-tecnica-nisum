package co.com.nisum.usuarios.domain.repository;

import co.com.nisum.usuarios.domain.common.Usuario;

import java.util.UUID;

public interface UsuarioRepository {

    Boolean existeCorreo(String email);

    Usuario registrarUsuario(Usuario usuario);

    Usuario actualizarUsuario(Usuario request);

    Usuario consultarPorId(UUID id);

    Usuario consultarPorEmail(String email);
}
