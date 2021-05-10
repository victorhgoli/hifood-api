package com.hifood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hifood.domain.exception.EntidadeEmUsoException;
import com.hifood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.hifood.domain.model.FormaPagamento;
import com.hifood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_FORMA_PAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}

	@Transactional
	public void excluir(Long restauranteId) {
		try {
			formaPagamentoRepository.deleteById(restauranteId);
			formaPagamentoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradoException(restauranteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMA_PAGAMENTO_EM_USO, restauranteId));
		}
	}

	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradoException(formaPagamentoId));
	}

}
