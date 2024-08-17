package co.com.nisum.usuarios.infraestructure.entrypoints.jwt;

import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.JwtAppServices;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class JwtAuthenticationFilterTest {

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private JwtAppServices jwtAppServices;
    @Mock
    private UsuarioAppServices usuarioAppServices;
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    @DisplayName("Debe validar que la validacion del token sea correcto y se agregue al contexto.")
    void testDoFilterInternal() throws HandledException, ServletException, IOException {
        //ARRANGE
        String token = "tokenValido";
        String username = "testUser";

        when(this.httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn("Bearer " + token);
        when(this.jwtAppServices.obtenerNombreDesdeToken(token)).thenReturn(username);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetails.getUsername()).thenReturn("testUser");
        when(userDetails.getAuthorities()).thenReturn(anyCollection());
        when(this.usuarioAppServices.obtenerUserDetails(username)).thenReturn(userDetails);
        when(this.jwtAppServices.elTokenEsValido(token, userDetails)).thenReturn(true);
        //ACTUATOR
        jwtAuthenticationFilter.doFilterInternal(httpServletRequest, httpServletResponse, filterChain);
        //ASSERT
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        assertNotNull(authenticationToken, "Se obtuvo una autenticacion de token exitosa.");

        verify(filterChain).doFilter(httpServletRequest, httpServletResponse);
    }
}