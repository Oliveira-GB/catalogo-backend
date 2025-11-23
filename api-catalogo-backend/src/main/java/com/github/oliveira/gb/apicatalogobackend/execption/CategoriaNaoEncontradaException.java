package com.github.oliveira.gb.apicatalogobackend.execption;

public class CategoriaNaoEncontradaException extends RuntimeException{
    public CategoriaNaoEncontradaException(String msg){
        super(msg);
    }
}