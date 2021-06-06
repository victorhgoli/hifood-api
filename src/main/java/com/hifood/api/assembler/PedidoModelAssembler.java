package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.PedidoController;
import com.hifood.api.model.PedidoModel;
import com.hifood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HifoodLinks hiFoodlinks;

	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		var pedidoModel = createModelWithId(pedido.getCodigo(), pedido);

		modelMapper.map(pedido, pedidoModel);

		pedidoModel.add(hiFoodlinks.linkToPedidos());

		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(hiFoodlinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		
		if (pedido.podeSerCancelado()) {
			pedidoModel.add(hiFoodlinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoModel.add(hiFoodlinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}

		pedidoModel.getRestaurante().add(hiFoodlinks.linkToRestaurante(pedido.getRestaurante().getId()));

		pedidoModel.getCliente().add(hiFoodlinks.linkToUsuario(pedido.getCliente().getId()));

		pedidoModel.getFormaPagamento().add(hiFoodlinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));

		pedidoModel.getEndereco().getCidade()
				.add(hiFoodlinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));

		pedidoModel.getItensPedido().forEach(item -> item.add(
				hiFoodlinks.linkToProduto(pedidoModel.getRestaurante().getId(), item.getProduto().getId(), "produto")));

		return pedidoModel;
	}

}
