package com.vhworks.hifoodapi.domain.service;

import com.vhworks.hifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.vhworks.hifoodapi.domain.model.Restaurante;
import com.vhworks.hifoodapi.domain.repository.RestauranteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {


    @Autowired
    RestauranteRepository restauranteRepository;
    
    public Restaurante salvar(Restaurante restaurante){
        return restauranteRepository.salvar(restaurante);
    }


    public void excluir(Long id){
        try{

            restauranteRepository.remover(id);
        }catch(EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código %d", id));
        }
    }
}
