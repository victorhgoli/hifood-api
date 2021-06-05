package com.hifood.domain.event;

import com.hifood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {
		private Pedido pedido;

}
