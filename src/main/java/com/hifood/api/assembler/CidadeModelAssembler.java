package com.hifood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hifood.api.model.CidadeModel;
import com.hifood.domain.model.Cidade;

@Component
public class CidadeModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade estado) {
		return modelMapper.map(estado, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
	}

}
