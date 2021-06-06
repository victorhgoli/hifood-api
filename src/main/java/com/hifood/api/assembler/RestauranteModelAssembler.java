package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.RestauranteController;
import com.hifood.api.model.RestauranteModel;
import com.hifood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLinks;
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	public RestauranteModel toModel(Restaurante restaurante) {
		
		var restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		
		restauranteModel.add(hiFoodLinks.linkToRestaurantes("restaurantes"));
		restauranteModel.add(hiFoodLinks.linkToRestauranteFormaPagamento(restaurante.getId(), "formas-pagamento"));
		restauranteModel.add(hiFoodLinks.linkToRestauranteUsuarioResponsavel(restaurante.getId(),"responsaveis"));
		
		if(restaurante.getAberto()) {
			restauranteModel.add(hiFoodLinks.linkToFechamentoRestaurante(restaurante.getId(), "fechar"));
		}else {
			restauranteModel.add(hiFoodLinks.linkToAberturaRestaurante(restaurante.getId(), "abrir"));
		}
		
		if(restaurante.getAtivo()) {
			restauranteModel.add(hiFoodLinks.linkToInativacaoRestaurante(restaurante.getId(), "inativar"));
		}else {
			restauranteModel.add(hiFoodLinks.linkToAtivacaoRestaurante(restaurante.getId(), "ativar"));
		}
		
		restauranteModel.getCozinha().add(hiFoodLinks.linkToCozinha(restauranteModel.getCozinha().getId()));
		restauranteModel.getEndereco().getCidade().add(hiFoodLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
		
		return restauranteModel;
	}


}