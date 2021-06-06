package com.hifood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.HifoodLinks;
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

	@Autowired
	private HifoodLinks hiFoodLinks;

	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		CollectionModel<UsuarioModel> usuariosResponsavelModel = usuarioModelAssembler
				.toCollectionModel(restaurante.getUsuarioResponsavel()).removeLinks()
				.add(hiFoodLinks.linkToResponsaveisRestaurante(restauranteId))
				.add(hiFoodLinks.linkToRestauranteUsuarioResponsavelAssociacao(restauranteId, "associar"));

		usuariosResponsavelModel.forEach(usuarioResponsavelModel -> {
			usuarioResponsavelModel.add(hiFoodLinks.linkToRestauranteUsuarioResponsavelDesassociacao(restauranteId,
					usuarioResponsavelModel.getId(), "desassociar"));
		});

		return usuariosResponsavelModel;
	}

	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarUsuario(restauranteId, usuarioId);

		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarUsuario(restauranteId, usuarioId);

		return ResponseEntity.noContent().build();
	}
}
