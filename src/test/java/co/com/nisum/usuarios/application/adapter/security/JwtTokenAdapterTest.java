package co.com.nisum.usuarios.application.adapter.security;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class JwtTokenAdapterTest {

    @InjectMocks
    private JwtTokenAdapter jwtTokenAdapter;

    private Usuario usuario;
    private String token;


    @BeforeEach
    void setUp() {
        openMocks(this);
        ReflectionTestUtils.setField(
                jwtTokenAdapter, "secretKey", "TESTJKAFDHLKAHJDFOASJR0OW43I45OW3ENTROWE4JT03O4TJR3W");
        usuario = Usuario.builder().email("nisum@test.com").build();
        token = this.jwtTokenAdapter.generarToken(usuario);
    }

    @Test
    @DisplayName("Debe generar el token para el usuarios desde el email y agregarle un tiempo de expiracion.")
    void testGenerarToken() {
        //ARRANGE
        Usuario request = mock(Usuario.class);
        String actual = this.jwtTokenAdapter.generarToken(request);
        //ASSERT
        assertTrue(actual.startsWith("ey"), "Se obtiene un token correcto.");
    }

    @Test
    @DisplayName("Validar si un token es valido comparado contra el UserDetails.")
    void testElTokenEsValido() throws HandledException {
        //ARRANGE
        UserDetails userDetails = mock(UserDetails.class);
        //ACTUATOR
        when(userDetails.getUsername()).thenReturn("nisum@test.com");

        Boolean actual = this.jwtTokenAdapter.elTokenEsValido(token, userDetails);
        //ASSERT
        assertTrue(actual, "El token debe ser valido.");
    }

    @Test
    @DisplayName("Debe obtener el username del token.")
    void testObtenerNombreDesdeToken() {
        //ARRANGE
        //ACTUATOR
        String username = this.jwtTokenAdapter.obtenerNombreDesdeToken(token);
        //ASSERT
        assertEquals(usuario.getEmail(), username, "El username obtenido es igual al esperado.");
    }
}