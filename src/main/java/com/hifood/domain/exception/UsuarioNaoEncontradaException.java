package com.hifood.domain.exception;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public UsuarioNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradaException(Long estadoId) {
        this(String.format("Não existe um cadastro de usuario com código %d",estadoId));
    }

}
