package com.hifood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.hifood.domain.event.PedidoConfirmadoEvent;
import com.hifood.domain.service.EnvioEmailService;
import com.hifood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoPedidoConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;

	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {

		var pedido = event.getPedido();
		
		System.out.println("passou listener");

		var build = Mensagem.builder().assunto(pedido.getRestaurante().getNome() + " - Pedido Confirmado")
				.corpo("pedido-confirmado.html").destinatario(pedido.getCliente().getEmail()).variavel("pedido", pedido)
				.build();

		envioEmail.enviar(build);
	}

}
