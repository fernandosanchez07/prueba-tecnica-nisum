package co.com.nisum.usuarios.infraestructure.entrypoints.bean;

import co.com.nisum.usuarios.application.adapter.AutenticacionAdapter;
import co.com.nisum.usuarios.application.adapter.security.JwtTokenAdapter;
import co.com.nisum.usuarios.application.adapter.UsuarioAdapter;
import co.com.nisum.usuarios.application.port.AutenticacionPort;
import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.repository.ContactoTelefonoRepository;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public UsuarioPort getUsuarioPort(UsuarioRepository usuarioRepository) {
        return new UsuarioAdapter(usuarioRepository);
    }

    @Bean
    public AutenticacionPort getAutenticacionPort(UsuarioRepository usuarioRepository,
                                                  ContactoTelefonoRepository contactoTelefonoRepository,
                                                  JwtTokenPort jwtTokenPort,
                                                  AuthenticationManager authenticationManager,
                                                  PasswordEncoder passwordEncoder) {
        return new AutenticacionAdapter(
                usuarioRepository,
                contactoTelefonoRepository,
                jwtTokenPort,
                authenticationManager,
                passwordEncoder);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JwtTokenPort jwtTokenPort() {
        return new JwtTokenAdapter();
    }

}
