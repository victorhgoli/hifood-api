package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.hifood.api.HifoodLinks;
import com.hifood.api.controller.FormaPagamentoController;
import com.hifood.api.model.FormaPagamentoModel;
import com.hifood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HifoodLinks hiFoodLiks;
	
	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}

	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		var formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		
		 modelMapper.map(formaPagamento, formaPagamentoModel);
		 
		 formaPagamentoModel.add(hiFoodLiks.linkToFormasPagamento("formasPagamento"));
		
		return formaPagamentoModel; 
	}
	
	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities)
			.add(hiFoodLiks.linkToFormasPagamento());
	}
}