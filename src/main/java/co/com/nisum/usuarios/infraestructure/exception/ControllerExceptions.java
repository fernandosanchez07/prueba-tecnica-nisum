package co.com.nisum.usuarios.infraestructure.exception;

import co.com.nisum.usuarios.domain.exception.HandledException;
import co.com.nisum.usuarios.domain.response.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(HandledException.class)
    public ResponseEntity<ResponseError> controlarExcepcionPersonalizada(HandledException he) {

        return ResponseEntity.badRequest()
                .body(
                        ResponseError.builder()
                                .mensaje(he.getMessage())
                                .build()
                );
    }
}
