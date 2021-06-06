package com.hifood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.PermissaoModelAssembler;
import com.hifood.api.model.PermissaoModel;
import com.hifood.domain.repository.PermissaoRepository;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

	@Autowired
	private PermissaoRepository usuarioRepository;


	@Autowired
	private PermissaoModelAssembler usuarioModelAssembler;


	@GetMapping
	public CollectionModel<PermissaoModel> listar() {
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}

}
