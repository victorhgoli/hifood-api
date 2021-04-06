package com.vhworks.hifoodapi.domain.repository;

import java.util.List;

import com.vhworks.hifoodapi.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
	FormaPagamento buscar(Long id);
	FormaPagamento salvar(FormaPagamento cozinha);
	void remover(FormaPagamento cozinha);
}
