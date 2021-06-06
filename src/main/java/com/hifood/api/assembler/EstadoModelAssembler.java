package com.hifood.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.EstadoController;
import com.hifood.api.model.EstadoModel;
import com.hifood.domain.model.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLinks;

	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);
	}
	
	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado); 

		modelMapper.map(estado, estadoModel);

//		estadoModel.add(linkTo(methodOn(EstadoController.class).listar()).withRel("estados"));
		estadoModel.add(hiFoodLinks.linkToEstados("estados"));

		return estadoModel;
	}

	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities).add(linkTo(EstadoController.class).withSelfRel());
	}

}