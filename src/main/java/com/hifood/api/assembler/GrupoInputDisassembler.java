package com.hifood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hifood.api.model.input.GrupoInput;
import com.hifood.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(GrupoInput estadoInput) {
		return modelMapper.map(estadoInput, Grupo.class);
	}

	public void copyToDomainObject(GrupoInput restauranteInput, Grupo estado) {
		modelMapper.map(restauranteInput, estado);
	}

}