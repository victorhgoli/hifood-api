package com.vhworks.hifoodapi.domain.repository;

import java.util.List;

import com.vhworks.hifoodapi.domain.model.Estado;

public interface EstadoRepository {

    List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long estadoId);
    
}
