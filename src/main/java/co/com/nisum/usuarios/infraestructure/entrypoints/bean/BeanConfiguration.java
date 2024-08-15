package co.com.nisum.usuarios.infraestructure.entrypoints.bean;

import co.com.nisum.usuarios.application.adapter.UsuarioAdapter;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.repository.UsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UsuarioPort getUsuarioPort(UsuarioRepository usuarioRepository){
        return new UsuarioAdapter(usuarioRepository);
    }
}
