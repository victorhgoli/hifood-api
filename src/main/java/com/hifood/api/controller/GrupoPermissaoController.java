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
import com.hifood.api.assembler.PermissaoModelAssembler;
import com.hifood.api.model.PermissaoModel;
import com.hifood.domain.model.Grupo;
import com.hifood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController {

	@Autowired
	private CadastroGrupoService cadastroGrupo;

	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@Autowired
	private HifoodLinks hifoodLinks;

	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler
				.toCollectionModel(grupo.getPermissoes()).removeLinks().add(hifoodLinks.linkToGrupoPermissoes(grupoId))
				.add(hifoodLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

		permissoesModel.getContent().forEach(permissaoModel -> {
			permissaoModel
					.add(hifoodLinks.linkToUsuarioGrupoDesassociacao(grupoId, permissaoModel.getId(), "desassociar"));
		});

		return permissoesModel;
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}
}