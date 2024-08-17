package co.com.nisum.usuarios.application.port;

import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenPort {


    String generarToken(Usuario usuario);

    Boolean elTokenEsValido(String token, UserDetails userDetails) throws HandledException;

    String obtenerNombreDesdeToken(String token);


}
