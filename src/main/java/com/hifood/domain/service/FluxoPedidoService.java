package com.hifood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hifood.domain.model.Pedido;
import com.hifood.domain.service.EnvioEmailService.Mensagem;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private EnvioEmailService envioEmail;

	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOufalhar(codigoPedido);
		pedido.confirmar();
		
		var build = Mensagem.builder()
						.assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
						.corpo("pedido-confirmado.html")
						.destinatario(pedido.getCliente().getEmail())
						.variavel("pedido", pedido)
						.build();

		envioEmail.enviar(build);
		
	}

	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOufalhar(codigoPedido);
		pedido.entregar();
	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOufalhar(codigoPedido);
		pedido.cancelar();

	}

}
