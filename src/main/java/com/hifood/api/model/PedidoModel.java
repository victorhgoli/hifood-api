package com.hifood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import com.hifood.domain.model.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoModel {
	private String codigo;
	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private EnderecoModel endereco;
	private UsuarioModel cliente;
	private RestauranteResumoModel restaurante;
	private FormaPagamentoModel formaPagamento;
	private StatusPedido status;
	private List<ItemPedidoModel> itensPedido;

}
