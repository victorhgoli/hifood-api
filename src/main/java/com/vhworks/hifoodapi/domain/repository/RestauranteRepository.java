package com.vhworks.hifoodapi.domain.repository;

import java.util.List;

import com.vhworks.hifoodapi.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante restaurante);
	void remover(Long id);
	
}
