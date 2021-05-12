package com.hifood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.model.Pedido;
import com.hifood.domain.model.StatusPedido;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOufalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Pedido de numero %s, não de ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
		}

		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	@Transactional
	public void entregar(Long pedidoId) {

		Pedido pedido = emissaoPedido.buscarOufalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(String.format("Pedido de numero %s, não de ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.ENTREGUE.getDescricao()));
		}

		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDataEntrega(OffsetDateTime.now());
	}

	public void cancelar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOufalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Pedido de numero %s, não de ser alterado de %s para %s",
					pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
		}

		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());

	}

}
