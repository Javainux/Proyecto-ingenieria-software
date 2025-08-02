package com.MiDoc.Midoc.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
    Map<String, Object> body = new HashMap<>();

    List<String> errores = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .toList();

    body.put("timestamp", LocalDateTime.now());
    body.put("status", HttpStatus.BAD_REQUEST.value());
    body.put("errors", errores);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
}
}
