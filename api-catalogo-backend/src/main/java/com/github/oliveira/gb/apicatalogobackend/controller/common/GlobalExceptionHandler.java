package com.github.oliveira.gb.apicatalogobackend.controller.common;

import com.github.oliveira.gb.apicatalogobackend.dto.ErroCampo;
import com.github.oliveira.gb.apicatalogobackend.dto.ErroResposta;
import com.github.oliveira.gb.apicatalogobackend.exception.CategoriaNaoEncontradaException;
import com.github.oliveira.gb.apicatalogobackend.exception.RecursoNaoEncontradoException;
import com.github.oliveira.gb.apicatalogobackend.exception.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return ErroResposta.validacao("Erro na validação", listaErros);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleCategoriaNaoEncontradaExeception(CategoriaNaoEncontradaException e){
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CategoriaNaoEncontradaException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCategoriaNaoEncontradaException(CategoriaNaoEncontradaException e){
        return ErroResposta.regraNegocio(e.getMessage());
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta HandleProductNaoEncontradoException(RecursoNaoEncontradoException e){
        return ErroResposta.naoEncontrado(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleRespostaPadrao(RuntimeException e){
        return ErroResposta.erroInterno(e.getMessage());
    }

}
