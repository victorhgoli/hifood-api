package com.hifood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "foto")
@Getter
@Setter
public class FotoProdutoModel extends RepresentationModel<FotoProdutoModel>{

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
}
