package com.vhworks.hifoodapi.domain.repository;

import java.util.List;

import com.vhworks.hifoodapi.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
