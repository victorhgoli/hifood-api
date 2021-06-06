package com.hifood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.hifood.api.assembler.CozinhaInputDisassembler;
import com.hifood.api.assembler.CozinhaModelAssembler;
import com.hifood.api.model.CozinhaModel;
import com.hifood.api.model.input.CozinhaInput;
import com.hifood.domain.exception.CozinhaNaoEncontradaException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.model.Cozinha;
import com.hifood.domain.repository.CozinhaRepository;
import com.hifood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;
	
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

	@GetMapping
	public PagedModel<CozinhaModel> listar(@PageableDefault(size = 4) Pageable pageable) {
		Page<Cozinha> cozinhasPage = cozinhaRepository.findAll(pageable);
		
		
		PagedModel<CozinhaModel> cozinhasPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cozinhaModelAssembler);
		
		return cozinhasPagedModel;
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return cozinhaModelAssembler.toModel(cadastroCozinha.buscarOuFalhar(cozinhaId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CozinhaModel adicionar(@Valid @RequestBody CozinhaInput cozinhaInput) {
		try {

			Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);

			return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinha));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @Valid @RequestBody CozinhaInput cozinhaInput) {
		try {

			Cozinha cozinhaAtual = cadastroCozinha.buscarOuFalhar(cozinhaId);

			cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

//			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

			return cozinhaModelAssembler.toModel(cadastroCozinha.salvar(cozinhaAtual));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}

	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinha.excluir(cozinhaId);
	}

}
