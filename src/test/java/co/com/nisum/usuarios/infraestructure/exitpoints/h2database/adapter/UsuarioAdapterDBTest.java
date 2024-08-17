package co.com.nisum.usuarios.infraestructure.exitpoints.h2database.adapter;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.entity.UsuarioEntity;
import co.com.nisum.usuarios.infraestructure.exitpoints.h2database.repository.UsuarioRepositoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioAdapterDBTest {

    @InjectMocks
    private UsuarioAdapterDB usuarioAdapterDB;

    @Mock
    private UsuarioRepositoryDB usuarioRepositoryDB;
    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe permitir consultar si existe o no un usuario en base de datos.")
    void testExisteCorreo() {
        //ARRANGE
        when(this.usuarioRepositoryDB.existsByEmail(anyString())).thenReturn(true);
        //ACTUATOR
        Boolean actual = this.usuarioAdapterDB.existeCorreo(anyString());
        //ASSERT
        assertTrue(actual, "El correo existe en base de datos.");
    }

    @Test
    @DisplayName("Debe registrar el usuario en base de datos.")
    void testRegistrarUsuario() {
        //ARRANGE
        Usuario request = Usuario.builder().build();
        UsuarioEntity entity = UsuarioEntity.builder().id(UUID.randomUUID()).build();
        when(this.usuarioRepositoryDB.saveAndFlush(any(UsuarioEntity.class))).thenReturn(entity);
        when(this.modelMapper.map(request, UsuarioEntity.class)).thenReturn(entity);
        Usuario usuarioGuardado = Usuario.builder().id(entity.getId()).build();
        when(this.modelMapper.map(entity, Usuario.class)).thenReturn(usuarioGuardado);
        //ACTUATOR
        Usuario actual = this.usuarioAdapterDB.registrarUsuario(request);
        //ASSERT
        assertEquals(usuarioGuardado.getId(), actual.getId(), "El id del usuario obtenido es igual al esperado.");
    }

    @Test
    @DisplayName("Debe retornar el usuario consultado por el id.")
    void testConsultarPorId() {
        //ARRANGE
        UUID id = UUID.randomUUID();
        Optional<UsuarioEntity> entityOptional = Optional.of(UsuarioEntity.builder().id(id).build());
        when(this.usuarioRepositoryDB.findById(id)).thenReturn(entityOptional);
        Usuario expected = Usuario.builder().id(id).build();
        when(this.modelMapper.map(entityOptional.get(), Usuario.class)).thenReturn(expected);
        //ACTUATOR
        Usuario actual = this.usuarioAdapterDB.consultarPorId(id);
        //ASSERT
        assertEquals(actual.getId(), id, "El id del usuario consulta esperado es igual al obtenido.");
    }


}