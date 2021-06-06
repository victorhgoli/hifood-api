package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.UsuarioController;
import com.hifood.api.model.UsuarioModel;
import com.hifood.domain.model.Usuario;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario,UsuarioModel>{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLinks;
	
	public UsuarioModelAssembler() {
		super(UsuarioController.class, UsuarioModel.class);
	}

	public UsuarioModel toModel(Usuario usuario) {
		var usuarioModel = createModelWithId(usuario.getId(), usuario);
		
		modelMapper.map(usuario, usuarioModel);
		
		usuarioModel.add(hiFoodLinks.linkToUsuarios("usuarios"));
		usuarioModel.add(hiFoodLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		
		return usuarioModel;
	}

}