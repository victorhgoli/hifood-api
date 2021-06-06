package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.RestauranteController;
import com.hifood.api.model.RestauranteBasicoModel;
import com.hifood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLinks;
	
	public RestauranteBasicoModelAssembler() {
		super(RestauranteController.class, RestauranteBasicoModel.class);
	}

	public RestauranteBasicoModel toModel(Restaurante restaurante) {
		
		var restauranteBasicoModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteBasicoModel);
		
		restauranteBasicoModel.add(hiFoodLinks.linkToRestaurantes("restaurantes"));
		
		restauranteBasicoModel.getCozinha().add(hiFoodLinks.linkToCozinha(restauranteBasicoModel.getCozinha().getId()));
		
		return restauranteBasicoModel;
	}


}