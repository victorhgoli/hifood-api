package com.hifood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hifood.domain.exception.EntidadeEmUsoException;
import com.hifood.domain.exception.ProdutoNaoEncontradoException;
import com.hifood.domain.model.Produto;
import com.hifood.domain.model.Restaurante;
import com.hifood.domain.repository.ProdutoRepository;

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

    @Transactional
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
