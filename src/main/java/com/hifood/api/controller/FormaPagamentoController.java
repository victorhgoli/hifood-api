package com.hifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.FormaPagamentoInputDisassembler;
import com.hifood.api.assembler.FormaPagamentoModelAssembler;
import com.hifood.api.model.FormaPagamentoModel;
import com.hifood.api.model.input.FormaPagamentoInput;
import com.hifood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.model.FormaPagamento;
import com.hifood.domain.repository.FormaPagamentoRepository;
import com.hifood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamento;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@Autowired
	private FormaPagamentoInputDisassembler estadoInputDisassembler;

	@GetMapping
	public List<FormaPagamentoModel> listar() {
		return formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
	}

	@GetMapping("/{formaPagamentoId}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
		return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		try {
			FormaPagamento formaPagamento = estadoInputDisassembler.toDomainObject(formaPagamentoInput);

			return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamento));
		} catch (FormaPagamentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
			@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		try {
			FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);

			estadoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

			return formaPagamentoModelAssembler.toModel(cadastroFormaPagamento.salvar(formaPagamentoAtual));
		} catch (FormaPagamentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long formaPagamentoId) {
		cadastroFormaPagamento.excluir(formaPagamentoId);
	}
}
