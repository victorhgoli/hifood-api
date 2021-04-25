package com.vhworks.hifood.domain.service;

import com.vhworks.hifood.domain.exception.EntidadeEmUsoException;
import com.vhworks.hifood.domain.exception.ProdutoNaoEncontradoException;
import com.vhworks.hifood.domain.model.Produto;
import com.vhworks.hifood.domain.model.Restaurante;
import com.vhworks.hifood.domain.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroRestauranteService cadastroRestaurante;

    public Produto buscarOuFalhar(Long id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto salvar(Produto produto) {
        Long restauranteId = produto.getRestaurante().getId();

        Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

        produto.setRestaurante(restaurante);

        return produtoRepository.save(produto);
    }

    public void excluir(Long produtoId) {
        try {
            produtoRepository.deleteById(produtoId);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(produtoId);

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Produto de código %d não pode ser removida, pois está em uso", produtoId));
        }

    }

}
