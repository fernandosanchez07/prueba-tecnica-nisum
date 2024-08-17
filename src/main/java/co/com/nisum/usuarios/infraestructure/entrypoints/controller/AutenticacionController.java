package co.com.nisum.usuarios.infraestructure.entrypoints.controller;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.requester.InicioSesionRequest;
import co.com.nisum.usuarios.domain.requester.UsuarioRegistroRequest;
import co.com.nisum.usuarios.domain.response.InicioSesionResponse;
import co.com.nisum.usuarios.domain.response.UsuarioRegistroResponse;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.AutenticacionAppServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Validated
public class AutenticacionController {

    private final AutenticacionAppServices autenticacionAppServices;

    @PostMapping(value = "/register")
    public ResponseEntity<UsuarioRegistroResponse> registrar(@Valid @RequestBody UsuarioRegistroRequest request)
            throws HandledException {
        return new ResponseEntity(this.autenticacionAppServices.registrarUsuario(request), HttpStatus.CREATED);
    }

        @PostMapping(value = "/login")
    public ResponseEntity<InicioSesionResponse> iniciarSesion(@Valid @RequestBody InicioSesionRequest request)
            throws HandledException {
        return ResponseEntity.ok(this.autenticacionAppServices.iniciarSesion(request));
    }
}
