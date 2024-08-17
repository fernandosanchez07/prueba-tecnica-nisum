package co.com.nisum.usuarios.infraestructure.entrypoints.service;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAppServices {

    private JwtTokenPort jwtTokenPort;

    public String generarToken(Usuario usuario) {
        return this.jwtTokenPort.generarToken(usuario);
    }

    public Boolean elTokenEsValido(String token, UserDetails userDetails) throws HandledException{
        return this.jwtTokenPort.elTokenEsValido(token, userDetails);
    }

    public String obtenerNombreDesdeToken(String token){
        return this.jwtTokenPort.obtenerNombreDesdeToken(token);
    }
}
