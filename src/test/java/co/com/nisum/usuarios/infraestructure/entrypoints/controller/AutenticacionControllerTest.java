package co.com.nisum.usuarios.infraestructure.entrypoints.controller;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.InicioSesionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioActualizacionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.InicioSesionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioActualizacionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.AutenticacionAppServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.openMocks;

class AutenticacionControllerTest {

    @InjectMocks
    private AutenticacionController autenticacionController;

    @Mock
    private AutenticacionAppServices autenticacionAppServices;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe validar que se registre el usuario.")
    void testRegistrar() throws HandledException {
        //ARRANGE
        UsuarioRegistroRequest request = mock(UsuarioRegistroRequest.class);
        //ACTUATOR
        ResponseEntity<UsuarioRegistroResponse> actual = this.autenticacionController.registrar(request);
        //ASSERT
        assertEquals(actual.getStatusCode().value(), 201, "El codigo de estado esperado es igual al obtenido.");
    }

    @Test
    @DisplayName("Debe validar que un usuario inicie sesion de manera correcta si se encuentra registrado.")
    void testIniciarSesion() throws HandledException {
        //ARRANGE
        InicioSesionRequest request = mock(InicioSesionRequest.class);
        //ACTUATOR
        ResponseEntity<InicioSesionResponse> actual = this.autenticacionController.iniciarSesion(request);
        //ASSERT
        assertEquals(actual.getStatusCode().value(), 200, "El codigo de estado esperado es igual al obtenido.");
    }
}