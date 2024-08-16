package co.com.nisum.usuarios.infraestructure.entrypoints.controller;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Validated
public class UsuarioController {

    private final UsuarioAppServices usuarioAppServices;

    @PostMapping(value = "/register")
    public ResponseEntity<UsuarioResponse> registrarUsuario(@Valid @RequestBody UsuarioRequest request)
            throws HandledException {
        return ResponseEntity.ok(this.usuarioAppServices.registrarUsuario(request));
    }
}
