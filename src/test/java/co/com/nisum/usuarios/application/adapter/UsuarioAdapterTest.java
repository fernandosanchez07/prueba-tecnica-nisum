package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.response.UsuarioActualizacionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioAdapterTest {

    @InjectMocks
    private UsuarioAdapter usuarioAdapter;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe obtener el UserDetails a partir del username.")
    void testObtenerUserDetails() {
        //ARRANGE
        Usuario usuario = Usuario.builder().email("nisum@test.com").password("test1").build();
        when(this.usuarioRepository.consultarPorEmail(anyString())).thenReturn(usuario);
        //ACTUATOR
        UserDetails actual = this.usuarioAdapter.obtenerUserDetails(anyString());
        //ASSERT
        assertNotNull(actual, "Se obtiene el UserDetails a partir del username.");
    }

    @Test
    @DisplayName("Debe validar que se actualice el usuario y devuelva la fecha de modificacion.")
    void testActualizarUsuario() throws HandledException {
        //ARRANGE
        UsuarioActualizacionRequest request = UsuarioActualizacionRequest.builder().id(UUID.randomUUID()).build();

        Usuario usuario = Usuario.builder().build();
        when(this.usuarioRepository.consultarPorId(request.getId())).thenReturn(usuario);
        Usuario usuarioActualizado = Usuario.builder().fechaActualizacion(LocalDateTime.now()).build();
        when(this.usuarioRepository.actualizarUsuario(usuario)).thenReturn(usuarioActualizado);
        //ACTUATOR
        UsuarioActualizacionResponse actual = this.usuarioAdapter.actualizarUsuario(request);
        //ASSERT
        assertEquals(
                actual.getModified(), usuarioActualizado.getFechaActualizacion(), "La fecha de actualizacion corresponde con la emitida.");
    }
}