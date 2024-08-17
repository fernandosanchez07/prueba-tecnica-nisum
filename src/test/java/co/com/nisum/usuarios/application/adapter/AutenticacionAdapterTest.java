package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.domain.requester.InicioSesionRequest;
import co.com.nisum.usuarios.domain.requester.TelefonoRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.InicioSesionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class AutenticacionAdapterTest {

    @InjectMocks
    private AutenticacionAdapter autenticacionAdapter;

    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private ContactoTelefonoRepository contactoTelefonoRepository;
    @Mock
    private JwtTokenPort jwtTokenPort;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UsuarioRegistroRequest usuarioRegistroRequest;

    @BeforeEach
    void setUp() {
        openMocks(this);

        usuarioRegistroRequest =
                UsuarioRegistroRequest.builder()
                        .email("nisum@test.com")
                        .name("nisum")
                        .password("test1")
                        .phones(
                                List.of(
                                        TelefonoRequest.builder()
                                                .number("1")
                                                .cityCode("1")
                                                .countryCode("1")
                                                .build()
                                )
                        )
                        .build();
    }

    @Test
    @DisplayName("Debe validar que se registre un usuario de manera exitosa.")
    void testRegistrarUsuario() throws HandledException {
        //ARRANGE
        Usuario expected =
                Usuario.builder()
                        .id(UUID.randomUUID())
                        .build();
        when(this.usuarioRepository.registrarUsuario(any(Usuario.class))).thenReturn(expected);
        //ACTUATOR
        UsuarioRegistroResponse actual = this.autenticacionAdapter.registrarUsuario(usuarioRegistroRequest);
        //ASSERT
        assertEquals(expected.getId(), actual.getId(), "El id del usuario obtenido es igual al esperado.");
    }

    @Test
    @DisplayName("Debe retornar una excepcion cuando se intente registrar un email que ya se encuentra en base de datos.")
    void testRegistrarUsuarioEmailRepetido() {
        //ARRANGE
        when(this.usuarioRepository.existeCorreo(usuarioRegistroRequest.getEmail())).thenReturn(true);
        assertThrows(HandledException.class, () -> {
            this.autenticacionAdapter.registrarUsuario(usuarioRegistroRequest);
        }, "Debe lanzar una excepcion personalizada.");
    }

    @Test
    @DisplayName("Debe validar que inicie sesion de manera correcta.")
    void testIniciarSesion() throws HandledException {
        //ARRANGE
        InicioSesionRequest request = InicioSesionRequest.builder().email("nisum@test.com").password("test1").build();
        Usuario usuarioRegistrado = Usuario.builder().build();
        when(this.usuarioRepository.existeCorreo(request.getEmail())).thenReturn(true);
        when(this.usuarioRepository.consultarPorEmail(anyString())).thenReturn(usuarioRegistrado);
        when(this.jwtTokenPort.generarToken(any(Usuario.class))).thenReturn(anyString());
        //ACTUATOR
        InicioSesionResponse actual = this.autenticacionAdapter.iniciarSesion(request);
        //ASSERT
        assertNotNull(actual.getToken(), "Se obtuvo un token para el inicio de sesion.");
    }

    @Test
    @DisplayName("Debe retornar una excepcion al intentar iniciar sesion con un usuario que no existe.")
    void testIniciarSesionUsuarioNoRegistrado() throws HandledException {
        //ARRANGE
        InicioSesionRequest request = InicioSesionRequest.builder().email("nisum@test.com").password("test1").build();
        when(this.usuarioRepository.existeCorreo(request.getEmail())).thenReturn(false);
        //ACTUATOR -ASSERT
        assertThrows(HandledException.class, () -> {
            this.autenticacionAdapter.iniciarSesion(request);
        }, "Debe lanzar una excepcion personalizada.");
        //ASSERT
    }
}
