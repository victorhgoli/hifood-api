package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hifood.api.model.input.EstadoInput;
import com.hifood.api.model.input.FormaPagamentoInput;
import com.hifood.domain.model.Cozinha;
import com.hifood.domain.model.Estado;
import com.hifood.domain.model.FormaPagamento;
import com.hifood.domain.model.Restaurante;

@Component
public class FormaPagamentoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public FormaPagamento toDomainObject(FormaPagamentoInput formaPagamentoInput) {
		return modelMapper.map(formaPagamentoInput, FormaPagamento.class);
	}

	public void copyToDomainObject(FormaPagamentoInput formaRestauranteInput, FormaPagamento formaPagamento) {
		modelMapper.map(formaRestauranteInput, formaPagamento);
	}

}