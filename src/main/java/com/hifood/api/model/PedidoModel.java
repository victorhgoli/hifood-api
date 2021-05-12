package com.hifood.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.hifood.domain.model.Endereco;
import com.hifood.domain.model.FormaPagamento;
import com.hifood.domain.model.ItemPedido;
import com.hifood.domain.model.Restaurante;
import com.hifood.domain.model.StatusPedido;
import com.hifood.domain.model.Usuario;

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
