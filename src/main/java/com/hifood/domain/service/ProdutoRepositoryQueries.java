package com.hifood.domain.service;

import com.hifood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto foto);
	void delete(FotoProduto foto);
}
