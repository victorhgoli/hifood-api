package com.hifood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.HifoodLinks;

@RestController
@RequestMapping
public class RootEntryPointController {
	
	@Autowired
	private HifoodLinks links;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		rootEntryPointModel.add(links.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(links.linkToPedidos("pedidos"));
		rootEntryPointModel.add(links.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(links.linkToGrupos("grupos"));
		rootEntryPointModel.add(links.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(links.linkToPermissoes("permissoes"));
		rootEntryPointModel.add(links.linkToFormasPagamento("formasPagamento"));
		rootEntryPointModel.add(links.linkToEstados("estados"));
		rootEntryPointModel.add(links.linkToCidades("cidades"));
		rootEntryPointModel.add(links.linkToEstatisticas("estatisticas"));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel>{
		
	}

}
