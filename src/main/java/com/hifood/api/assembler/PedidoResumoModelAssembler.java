package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.PedidoController;
import com.hifood.api.model.PedidoResumoModel;
import com.hifood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLinks;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	public PedidoResumoModel toModel(Pedido pedido) {
		var pedidoResumo = createModelWithId(pedido.getCodigo(), pedido);
		
		pedidoResumo.add(hiFoodLinks.linkToPedidos());
		pedidoResumo.add(hiFoodLinks.linkToRestaurante(pedido.getRestaurante().getId()));
		pedidoResumo.add(hiFoodLinks.linkToUsuario(pedido.getCliente().getId()));
		
		
		return modelMapper.map(pedido, PedidoResumoModel.class);
	}

}
