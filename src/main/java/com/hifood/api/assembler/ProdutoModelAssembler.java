package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.RestauranteProdutoController;
import com.hifood.api.model.ProdutoModel;
import com.hifood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hifoodLinks;
	
	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	public ProdutoModel toModel(Produto produto) {
		var produtoModel = createModelWithId(
                produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoModel);
		
		produtoModel.add(hifoodLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
		produtoModel.add(hifoodLinks.linkToFotoProduto(produto.getRestaurante().getId(), produto.getId(), "foto"));
		
		return produtoModel;
	}
	
	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities);
	}

}