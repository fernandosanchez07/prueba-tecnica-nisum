package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.adapter;

import co.com.nisum.usuarios.domain.common.ContactoTelefono;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity.ContactoTelefonoEntity;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.repository.ContactoTelefonoRepositoryDB;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactoTelefonoAdapterDB implements ContactoTelefonoRepository {

    private final ContactoTelefonoRepositoryDB contactoTelefonoRepositoryDB;
    private final ModelMapper modelMapper;

    @Override
    public void guardarTelefonos(List<ContactoTelefono> telefonos) {
        List<ContactoTelefonoEntity> telefonosEntity =
                telefonos.stream()
                        .map(telefono -> this.modelMapper.map(telefono, ContactoTelefonoEntity.class))
                        .toList();

        this.contactoTelefonoRepositoryDB.saveAll(telefonosEntity);
    }
}
