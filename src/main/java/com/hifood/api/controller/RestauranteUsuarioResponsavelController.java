package com.hifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.UsuarioModelAssembler;
import com.hifood.api.model.UsuarioModel;
import com.hifood.domain.model.Restaurante;
import com.hifood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@GetMapping
	public List<UsuarioModel> listar(@PathVariable Long restauranteId) {

		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
		return usuarioModelAssembler.toCollectionModel(restaurante.getUsuarioResponsavel());
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarUsuario(restauranteId, usuarioId);
	}

	@PutMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarUsuario(restauranteId, usuarioId);
	}
}
