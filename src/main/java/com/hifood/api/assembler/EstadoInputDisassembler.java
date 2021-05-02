package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hifood.api.model.input.EstadoInput;
import com.hifood.domain.model.Cozinha;
import com.hifood.domain.model.Estado;
import com.hifood.domain.model.Restaurante;

@Component
public class EstadoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoInput estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}

	public void copyToDomainObject(EstadoInput restauranteInput, Estado estado) {
		modelMapper.map(restauranteInput, estado);
	}

}