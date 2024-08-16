package co.com.nisum.usuarios.domain.repository;

public interface UsuarioRepository {

    Boolean existeCorreo(String email);
}
