package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.repository;

import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepositoryDB extends JpaRepository<UsuarioEntity, UUID> {

    Boolean existsByEmail(String email);
}
