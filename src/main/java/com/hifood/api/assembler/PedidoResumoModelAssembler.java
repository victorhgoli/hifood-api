package com.hifood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.controller.PedidoController;
import com.hifood.api.model.PedidoResumoModel;
import com.hifood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	public PedidoResumoModel toModel(Pedido pedido) {
		var pedidoResumo = createModelWithId(pedido.getId(), pedido);
		
		pedidoResumo.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}

}
