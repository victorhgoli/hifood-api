package com.hifood.domain.service;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hifood.domain.model.FotoProduto;
import com.hifood.domain.repository.ProdutoRepository;
import com.hifood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FotoStorageService fotoStorage;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream inputStream) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String novoNomeArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			produtoRepository.delete(fotoExistente.get());
		}

		FotoProduto fotoProduto = produtoRepository.save(foto);
		produtoRepository.flush();

		var novaFoto = NovaFoto.builder().nome(novoNomeArquivo).inputStream(inputStream).build();

		fotoStorage.armazenar(novaFoto);

		return fotoProduto;
	}

}
