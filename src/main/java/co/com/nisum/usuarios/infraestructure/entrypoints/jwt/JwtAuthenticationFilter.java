package co.com.nisum.usuarios.infraestructure.entrypoints.jwt;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.JwtAppServices;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAppServices jwtAppServices;

    private final UsuarioAppServices usuarioAppServices;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = obtenerTokenSolicitud(request);
        try {
            if (StringUtils.hasText(token)) {
                String username = this.jwtAppServices.obtenerNombreDesdeToken(token);

                if (Objects.nonNull(username) &&
                        Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                    UserDetails userDetails = this.usuarioAppServices.obtenerUserDetails(username);
                    if (this.jwtAppServices.elTokenEsValido(token, userDetails)) {

                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }

            }
        } catch (HandledException e) {
            filterChain.doFilter(request, response);
        }

        filterChain.doFilter(request, response);
    }

    public String obtenerTokenSolicitud(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;

    }
}
