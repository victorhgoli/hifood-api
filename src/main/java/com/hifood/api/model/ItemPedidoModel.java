package com.hifood.api.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Relation(collectionRelation = "itens")
@Setter
public class ItemPedidoModel extends RepresentationModel<ItemPedidoModel>{

	private Long id;
	private ProdutoModel produto;
	private int quantidade;
	private BigDecimal precoUnitario;
	private BigDecimal precoTotal;
	private String observacao;

}
