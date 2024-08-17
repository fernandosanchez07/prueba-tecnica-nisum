package co.com.nisum.usuarios.infraestructure.entrypoints.controller;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.response.UsuarioActualizacionResponse;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioAppServices usuarioAppServices;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testActualizarNombreUsuario() throws HandledException {
        //ARRANGE
        UsuarioActualizacionRequest request = mock(UsuarioActualizacionRequest.class);
        //ACTUATOR
        ResponseEntity<UsuarioActualizacionResponse> actual = this.usuarioController.actualizarNombreUsuario(request);
        //ASSERT
        assertEquals(actual.getStatusCode().value(), 200, "EL codigo de estado esperado es igual al obtenido.");
    }
}