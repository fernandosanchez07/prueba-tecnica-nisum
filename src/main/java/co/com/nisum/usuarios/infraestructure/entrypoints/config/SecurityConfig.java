package co.com.nisum.usuarios.infraestructure.entrypoints.config;


import co.com.nisum.usuarios.application.port.JwtTokenPort;
import co.com.nisum.usuarios.application.port.UsuarioPort;
import co.com.nisum.usuarios.infraestructure.entrypoints.jwt.JwtAuthenticationFilter;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.JwtAppServices;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAppServices jwtAppServices;

    private final UsuarioAppServices usuarioAppServices;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtAppServices, usuarioAppServices);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .headers(header ->
                        header.frameOptions(
                                frame -> frame.disable())
                )
                .authorizeHttpRequests(authRequest ->
                        authRequest.requestMatchers(urlsAExluir()).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private String[] urlsAExluir() {
        return new String[]{
                // -- Swagger UI v3 (OpenAPI)
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/doc/**",
                //Api usuarios
                "/h2/**",
                "/auth/register",
                "/auth/login"
        };
    }
}
