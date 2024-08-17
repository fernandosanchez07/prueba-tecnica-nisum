package co.com.nisum.usuarios.infraestructure.exception;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.response.ResponseError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class ControllerExceptionsTest {

    @InjectMocks
    private ControllerExceptions controllerExceptions;

    @Mock
    private HandledException handledException;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe validar que se capturen las excepciones personalizadas.")
    void testControlarExcepcionPersonalizada() {
        //ARRANGE
        String mensajeError = "Error";
        when(this.handledException.getMessage()).thenReturn(mensajeError);
        //ACTUATOR
        ResponseEntity<ResponseError> actual =
                this.controllerExceptions.controlarExcepcionPersonalizada(handledException);
        //ASSERT
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode(), "El codigo a obtenerse debe ser el 400.");
    }
}