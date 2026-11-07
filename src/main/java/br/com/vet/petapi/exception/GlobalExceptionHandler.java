package br.com.vet.petapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(e -> erros.put(e.getField(), e.getDefaultMessage()));

        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("status", 400);
        corpo.put("erro", "Dados inválidos");
        corpo.put("campos", erros);
        return ResponseEntity.badRequest().body(corpo);
    }

    // JSON malformado ou idade com tipo errado (ex: "abc" ou 2.5)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonInvalido(HttpMessageNotReadableException ex) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("status", 400);
        corpo.put("erro", "Corpo da requisição inválido. A idade deve ser um número inteiro.");
        return ResponseEntity.badRequest().body(corpo);
    }

    @ExceptionHandler(AnimalNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> handleNaoEncontrado(AnimalNaoEncontradoException ex) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("status", 404);
        corpo.put("erro", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(corpo);
    }
}