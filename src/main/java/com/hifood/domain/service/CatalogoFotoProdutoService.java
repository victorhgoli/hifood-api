package com.hifood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hifood.domain.exception.FotoProdutoNaoEncontradoException;
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
		String nomeArquivoExistente = null;

		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);

		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}

		foto.setNomeArquivo(novoNomeArquivo);
		FotoProduto fotoProduto = produtoRepository.save(foto);
		produtoRepository.flush();

		var novaFoto = NovaFoto.builder().nome(foto.getNomeArquivo()).inputStream(inputStream).contentType(foto.getContentType()).build();

		fotoStorage.substituir(nomeArquivoExistente, novaFoto);

		return fotoProduto;
	}

	@Transactional
	public void excluir(Long restaranteId, Long produtoId) {
		FotoProduto fotoProduto = buscaOuFalhar(restaranteId, produtoId);

		fotoStorage.remover(fotoProduto.getNomeArquivo());
		produtoRepository.delete(fotoProduto);
	}

	public FotoProduto buscaOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradoException(restauranteId, produtoId));
	}

}
