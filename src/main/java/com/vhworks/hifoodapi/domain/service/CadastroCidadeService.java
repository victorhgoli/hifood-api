package com.vhworks.hifoodapi.domain.service;

import com.vhworks.hifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.vhworks.hifoodapi.domain.model.Cidade;
import com.vhworks.hifoodapi.domain.repository.CidadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.salvar(cidade);
    }

    public void excluir(Long cidadeId) {
        try {
            cidadeRepository.remover(cidadeId);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", cidadeId));
        }
    }
}
