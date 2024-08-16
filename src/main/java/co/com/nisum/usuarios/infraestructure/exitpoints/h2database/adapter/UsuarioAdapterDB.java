package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.adapter;

import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.repository.UsuarioRepositoryDB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAdapterDB implements UsuarioRepository {

    private final UsuarioRepositoryDB usuarioRepositoryDB;

    @Override
    public Boolean existeCorreo(String email) {
        return this.usuarioRepositoryDB.existsByEmail(email);
    }
}
