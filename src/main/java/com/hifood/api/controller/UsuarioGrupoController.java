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
import com.hifood.api.assembler.GrupoModelAssembler;
import com.hifood.api.model.GrupoModel;
import com.hifood.domain.model.Usuario;
import com.hifood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@Autowired
	private HifoodLinks links;

	@GetMapping
	public CollectionModel<GrupoModel> listar(@PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		CollectionModel<GrupoModel> gruposModel = grupoModelAssembler.toCollectionModel(usuario.getGrupos())
				.removeLinks().add(links.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

		gruposModel.getContent().forEach(grupoModel -> {
			grupoModel.add(links.linkToUsuarioGrupoDesassociacao(usuarioId, grupoModel.getId(), "desassociar"));
		});

		return gruposModel;
	}

	@PutMapping("{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
		return ResponseEntity.noContent().build();
	}

}
