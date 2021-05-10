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

import com.hifood.api.assembler.EstadoInputDisassembler;
import com.hifood.api.assembler.EstadoModelAssembler;
import com.hifood.api.model.EstadoModel;
import com.hifood.api.model.input.EstadoInput;
import com.hifood.domain.exception.EstadoNaoEncontradoException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.model.Estado;
import com.hifood.domain.repository.EstadoRepository;
import com.hifood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@GetMapping
	public List<EstadoModel> listar() {
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		return estadoModelAssembler.toModel(cadastroEstado.buscarOuFalhar(estadoId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		try {
			Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);

			return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInput estadoInput) {
		try {
			Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);

			estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

			return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstado.excluir(estadoId);
	}
}
