package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.adapter;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity.UsuarioEntity;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.repository.UsuarioRepositoryDB;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAdapterDB implements UsuarioRepository {

    private final ModelMapper modelMapper;
    private final UsuarioRepositoryDB usuarioRepositoryDB;

    @Override
    public Boolean existeCorreo(String email) {
        return this.usuarioRepositoryDB.existsByEmail(email);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity =
                this.usuarioRepositoryDB.saveAndFlush(
                        this.modelMapper.map(usuario, UsuarioEntity.class)
                );

        return this.modelMapper.map(usuarioEntity, Usuario.class);
    }
}
