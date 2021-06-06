package com.hifood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.hifood.domain.event.PedidoCanceladoEvent;
import com.hifood.domain.service.EnvioEmailService;
import com.hifood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoPedidoCanceladoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener
	public void aoCancelarPedido(PedidoCanceladoEvent event) {

		var pedido = event.getPedido();
		
		System.out.println("passou listener de cancelamento");

		var build = Mensagem.builder().assunto(pedido.getRestaurante().getNome() + " - Pedido Cancelado")
				.corpo("pedido-cancelado.html").destinatario(pedido.getCliente().getEmail()).variavel("pedido", pedido)
				.build();

		envioEmail.enviar(build);
	}

}
