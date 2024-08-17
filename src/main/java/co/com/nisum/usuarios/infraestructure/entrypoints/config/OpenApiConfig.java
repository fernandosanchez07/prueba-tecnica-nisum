package co.com.nisum.usuarios.infraestructure.entrypoints.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ApiRest usuarios")
                                .version("1.0")
                                .description("Api para registro de usuarios para prueba en NISUM.")
                );
    }
}
