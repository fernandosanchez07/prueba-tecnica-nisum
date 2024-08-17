package co.com.nisum.usuarios.infraestructure.entrypoints.bean;

import co.com.nisum.usuarios.application.adapter.JwtGenerator;
import co.com.nisum.usuarios.application.adapter.UsuarioAdapter;
import co.com.nisum.usuarios.application.port.JwtGeneratorPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import co.com.nisum.usuarios.infraestructure.entrypoints.config.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BeanConfiguration {

    @Bean
    public UsuarioPort getUsuarioPort(UsuarioRepository usuarioRepository,
                                      ContactoTelefonoRepository contactoTelefonoRepository,
                                      JwtGeneratorPort jwtGeneratorPort) {
        return new UsuarioAdapter(usuarioRepository, contactoTelefonoRepository, jwtGeneratorPort);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JwtGeneratorPort jwtGeneratorPort() {
        return new JwtGenerator();
    }

}
