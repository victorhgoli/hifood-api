package com.vhworks.hifoodapi.domain.repository;

import java.util.List;

import com.vhworks.hifoodapi.domain.model.Permissao;

public interface PermissaoRepository {

    List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao cozinha);
	void remover(Permissao cozinha);
    
}
