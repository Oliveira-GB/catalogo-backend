package com.github.oliveira.gb.apicatalogobackend.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta (int status, String mensagem, List<ErroCampo> erroCampos){
    public static ErroResposta erroInterno(String mensagem){
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), mensagem, List.of());
    }

    public static ErroResposta conflito(String mensagem){
        return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
    }

    public static ErroResposta validacao(String mensagem, List<ErroCampo> listaDeErros){
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), mensagem, listaDeErros);
    }

    public static ErroResposta regraNegocio(String mensagem){
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), mensagem, List.of());
    }
}
