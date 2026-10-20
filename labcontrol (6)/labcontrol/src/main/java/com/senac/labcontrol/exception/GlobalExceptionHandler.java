package com.senac.labcontrol.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> resposta(HttpStatus status, String msg, Object detalhes) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("timestamp", LocalDateTime.now().toString());
        corpo.put("status", status.value());
        corpo.put("erro", status.getReasonPhrase());
        corpo.put("mensagem", msg);
        if (detalhes != null) corpo.put("detalhes", detalhes);
        return ResponseEntity.status(status).body(corpo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> validacao(MethodArgumentNotValidException ex) {
        Map<String, String> campos = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
          .forEach(f -> campos.put(f.getField(), f.getDefaultMessage()));
        return resposta(HttpStatus.BAD_REQUEST, "Dados inválidos", campos);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, Object>> requisicaoInvalida(Exception ex) {
        return resposta(HttpStatus.BAD_REQUEST, "Requisição malformada ou com tipos inválidos", null);
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> naoEncontrado(RecursoNaoEncontradoException ex) {
        return resposta(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler({PatrimonioDuplicadoException.class, DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, Object>> conflito(Exception ex) {
        String msg = (ex instanceof PatrimonioDuplicadoException)
            ? ex.getMessage() : "Violação de integridade: patrimônio já cadastrado";
        return resposta(HttpStatus.CONFLICT, msg, null);
    }
}
