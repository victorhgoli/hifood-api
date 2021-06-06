package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.GrupoController;
import com.hifood.api.model.GrupoModel;
import com.hifood.domain.model.Grupo;

@Component
public class GrupoModelAssembler extends RepresentationModelAssemblerSupport<Grupo, GrupoModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks links;
	
	public GrupoModelAssembler() {
		super(GrupoController.class, GrupoModel.class);
	}

	public GrupoModel toModel(Grupo grupo) {
		var grupoModel = createModelWithId(grupo.getId(), grupo);
		
		modelMapper.map(grupo, grupoModel);
		
		grupoModel.add(links.linkToGrupos("grupos"));
		grupoModel.add(links.linkToPermissoesGrupo(grupo.getId(), "permissoes"));
		
		
		
		return grupoModel;
	}

}