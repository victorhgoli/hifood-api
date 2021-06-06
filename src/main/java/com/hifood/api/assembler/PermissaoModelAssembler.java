package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.PermissaoController;
import com.hifood.api.model.PermissaoModel;
import com.hifood.domain.model.Permissao;

@Component
public class PermissaoModelAssembler extends RepresentationModelAssemblerSupport<Permissao, PermissaoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks links;
	
	public PermissaoModelAssembler() {
		super(PermissaoController.class, PermissaoModel.class);
	}

	public PermissaoModel toModel(Permissao permissao) {
		var permissaoModel = createModelWithId(permissao.getId(), permissao);
		
		modelMapper.map(permissao, permissaoModel);
		
		return permissaoModel; 
	}
	
	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return super.toCollectionModel(entities).add(links.linkToPermissoes());
	}


}
