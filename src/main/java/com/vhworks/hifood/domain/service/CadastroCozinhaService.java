package com.vhworks.hifood.domain.service;

import com.vhworks.hifood.domain.exception.CozinhaNaoEncontradaException;
import com.vhworks.hifood.domain.exception.EntidadeEmUsoException;
import com.vhworks.hifood.domain.model.Cozinha;
import com.vhworks.hifood.domain.repository.CozinhaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroCozinhaService {

	private static final String COZINHA_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA_POIS_ESTÁ_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);

		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(COZINHA_DE_CÓDIGO_D_NÃO_PODE_SER_REMOVIDA_POIS_ESTÁ_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
