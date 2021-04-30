package com.hifood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    MENSAGEM_INCOMPREENSIVEL("/mensagem-icompreensivel", "Mensagem incompreensivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-informado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    PARAMETRO_INVALIDO("/parametro-invalido", "parametro invalido"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados invalidos"),
    ERRO_DE_SISTEMA("/erro-de-sistema","Erro de Sistema");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://hifood.com.br/" + path;
        this.title = title;
    }
}
