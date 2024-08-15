package co.com.nisum.usuarios.infraestructure.entrypoints.controller;

import co.com.nisum.usuarios.domain.requester.UsuarioRequest;
import co.com.nisum.usuarios.domain.response.UsuarioResponse;
import co.com.nisum.usuarios.infraestructure.entrypoints.service.UsuarioAppServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioAppServices usuarioAppServices;

    @PostMapping(value = "/register")
    public ResponseEntity<UsuarioResponse> registrarUsuario(@RequestBody UsuarioRequest request){
        return ResponseEntity.ok(this.usuarioAppServices.registrarUsuario(request));
    }
}
