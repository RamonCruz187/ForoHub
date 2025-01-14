package com.alura.forohub.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErrorValidacion>> tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<DatosErrorValidacion> tratarNullPointerException(NullPointerException e){
        var errores = new DatosErrorValidacion("error", e.getMessage());
        return ResponseEntity.badRequest().body(errores);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DatosErrorValidacion> tratarErrorGeneral(RuntimeException e){
        var errores = new DatosErrorValidacion("error", e.getMessage());
        return ResponseEntity.badRequest().body(errores);
    }



    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
