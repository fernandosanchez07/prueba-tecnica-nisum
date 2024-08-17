package co.com.nisum.usuarios.application.adapter;

import co.com.nisum.usuarios.application.port.JwtGeneratorPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.mensajes.MensajePersonalizado;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtGenerator implements JwtGeneratorPort {

    @Override
    public String generarToken(Usuario usuario) {
        return obtenerToken(new HashMap<>(), usuario);
    }

    private String obtenerToken(Map<String, Object> extraClaims, Usuario usuario) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

    }


    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("LAKJSDFLAKJDFLKAJSFLKJASFKH2H4L2S34534LMLM3M4L3L3L3L3L4LL4L4L43L334L34L3L4L34L34K34HOL2KH4JL2");
        return Keys.hmacShaKeyFor(keyBytes);
    }


    @Override
    public Boolean elTokenEsValido(String token, UserDetails userDetails) throws HandledException {

        String username = this.obtenerNombreDesdeToken(token);
        return username.equals(userDetails.getUsername()) && !elTokenExpiro(token);
    }

    @Override
    public String obtenerNombreDesdeToken(String token) {
        return obtenerClaim(token, Claims::getSubject);
    }

    public Claims obtenerClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }

    public <T> T obtenerClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = obtenerClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date obtenerFechaExpiracion(String token) {
        return obtenerClaim(token, Claims::getExpiration);
    }

    private Boolean elTokenExpiro(String token){
        return obtenerFechaExpiracion(token).before(new Date());
    }
}
