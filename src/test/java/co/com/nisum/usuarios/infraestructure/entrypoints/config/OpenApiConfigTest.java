package co.com.nisum.usuarios.infraestructure.entrypoints.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OpenApiConfigTest {

    @InjectMocks
    private OpenApiConfig openApiConfig;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe validar que se configure el OpenApi para la documentacion con swagger.")
    void testCustomOpenApi() {
        //ARRANGE
        Info infoMock = mock(Info.class);
        when(infoMock.getTitle()).thenReturn("ApiRest usuarios");
        when(infoMock.getVersion()).thenReturn("1.0");
        when(infoMock.getDescription()).thenReturn("Api para registro de usuario");

        OpenAPI expected = new OpenAPI().info(infoMock);
        //ACTUATOR
        OpenAPI actual = this.openApiConfig.customOpenApi();
        //ASSERT
        assertEquals(expected.getInfo().getTitle(), actual.getInfo().getTitle(), "El titulo actual es igual al esperado.");
    }
}