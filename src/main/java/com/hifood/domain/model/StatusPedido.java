package com.hifood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	CRIADO("Criado"), CONFIRMADO("Confirmado",CRIADO), ENTREGUE("Entregue", CONFIRMADO), CANCELADO("Cancelado",CRIADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;	

	private StatusPedido(String descricao, StatusPedido... statusAnt) {
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnt);
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public boolean naoPodeAlterarParaStatus(StatusPedido status) {
		return !status.statusAnteriores.contains(this);
	}

}