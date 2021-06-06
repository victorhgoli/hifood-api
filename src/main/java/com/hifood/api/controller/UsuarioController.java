package com.hifood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
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

import com.hifood.api.assembler.UsuarioInputDisassembler;
import com.hifood.api.assembler.UsuarioModelAssembler;
import com.hifood.api.model.UsuarioModel;
import com.hifood.api.model.input.SenhaInput;
import com.hifood.api.model.input.UsuarioComSenhaInput;
import com.hifood.api.model.input.UsuarioInput;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.exception.UsuarioNaoEncontradaException;
import com.hifood.domain.model.Usuario;
import com.hifood.domain.repository.UsuarioRepository;
import com.hifood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	

	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}

	@GetMapping("/{userId}")
	public UsuarioModel buscar(@PathVariable Long userId) {
		return usuarioModelAssembler.toModel(cadastroUsuario.buscarOuFalhar(userId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
		try {
			Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);

			return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuario));
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@PutMapping("/{userId}")
	public UsuarioModel atualizar(@PathVariable Long userId, @RequestBody @Valid UsuarioInput userInput) {
		try {
			Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(userId);

			usuarioInputDisassembler.copyToDomainObject(userInput, usuarioAtual);

			return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuarioAtual));
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}
	
	
	@PutMapping("/{userId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraSenha(@PathVariable Long userId, @RequestBody @Valid SenhaInput senhaInput) {
		try {
			cadastroUsuario.alterarSenha(userId, senhaInput.getSenhaAtual(), senhaInput.getNovaSenha());
		} catch (UsuarioNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e.getCause());
		}
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long userId) {
		cadastroUsuario.excluir(userId);
	}
}
