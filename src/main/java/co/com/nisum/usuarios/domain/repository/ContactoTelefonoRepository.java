package co.com.nisum.usuarios.domain.repository;

import co.com.nisum.usuarios.domain.common.ContactoTelefono;

import java.util.List;

public interface ContactoTelefonoRepository {
    void guardarTelefonos(List<ContactoTelefono> telefonos);
}
