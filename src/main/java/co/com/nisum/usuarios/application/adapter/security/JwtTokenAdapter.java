package co.com.nisum.usuarios.application.adapter.security;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.common.Usuario;
import co.com.nisum.usuarios.domain.exception.HandledException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenAdapter implements JwtTokenPort {

    @Value("${secretyKey}")
    private String secretKey;

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
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
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
