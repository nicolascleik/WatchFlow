package com.watchflow.WatchFlow.infrastructure.web;

import com.watchflow.WatchFlow.core.exceptions.NaoEncontradoException;
import com.watchflow.WatchFlow.core.exceptions.RegraNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public record ErroResponse(String erro, String mensagem, LocalDateTime timestamp) {}

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ErroResponse> handleRegraNegocio(RegraNegocioException ex) {
        ErroResponse erro = new ErroResponse("Regra de Negócio Violada", ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleNaoEncontrado(NaoEncontradoException ex) {
        ErroResponse erro = new ErroResponse("Recurso Não Encontrado", ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleExcecaoGenerica(Exception ex) {
        ErroResponse erro = new ErroResponse("Erro Interno no Servidor", "Ocorreu um erro inesperado. Tente novamente.", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}