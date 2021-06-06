package com.hifood.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.hifood.api.controller.CidadeController;
import com.hifood.api.controller.CozinhaController;
import com.hifood.api.controller.EstadoController;
import com.hifood.api.controller.FormaPagamentoController;
import com.hifood.api.controller.PedidoController;
import com.hifood.api.controller.RestauranteController;
import com.hifood.api.controller.RestauranteProdutoController;
import com.hifood.api.controller.RestauranteUsuarioResponsavelController;
import com.hifood.api.controller.UsuarioController;
import com.hifood.api.controller.UsuarioGrupoController;

@Component
public class HifoodLinks {
	public static final TemplateVariables PAGINACAO_VARIABLES = new TemplateVariables(
			new TemplateVariable("page", VariableType.REQUEST_PARAM),
			new TemplateVariable("size", VariableType.REQUEST_PARAM),
			new TemplateVariable("sort", VariableType.REQUEST_PARAM));

	public Link linkToPedidos() {
		var fitrosVariables = new TemplateVariables(new TemplateVariable("clientId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(fitrosVariables)), "pedidos");
	}

	public Link linkToRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteController.class).buscar(restauranteId)).withRel(rel);
	}

	public Link linkToRestaurante(Long restauranteId) {
		return linkToRestaurante(restauranteId, IanaLinkRelations.SELF.toString());
	}

	public Link linkToUsuario(Long id, String rel) {
		return linkTo(methodOn(UsuarioController.class).buscar(id)).withRel(rel);
	}

	public Link linkToUsuario(Long id) {
		return linkToUsuario(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToFormaPagamento(Long id, String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).buscar(id)).withRel(rel);
	}

	public Link linkToFormaPagamento(Long id) {
		return linkToFormaPagamento(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToCidade(Long id, String rel) {
		return linkTo(methodOn(CidadeController.class).buscar(id)).withRel(rel);
	}

	public Link linkToCidade(Long id) {
		return linkToCidade(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}

	public Link linkToProduto(Long restauranteId, Long produtoId) {
		return linkToProduto(restauranteId, produtoId, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToUsuarios(String rel) {
		return linkTo(UsuarioController.class).withRel(rel);
	}

	public Link linkToUsuarios() {
		return linkToUsuarios(IanaLinkRelations.SELF.value());
	}

	public Link linkToGruposUsuario(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).listar(usuarioId)).withRel(rel);
	}

	public Link linkToGruposUsuario(Long usuarioId) {
		return linkToGruposUsuario(usuarioId, IanaLinkRelations.SELF.value());
	}

	public Link linkToEstados(String rel) {
		return linkTo(methodOn(EstadoController.class).listar()).withRel(rel);
	}

	public Link linkToEstados() {
		return linkToEstados(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToCozinhas(String rel) {
		return linkTo(CozinhaController.class).withRel(rel);
	}

	public Link linkToCozinhas() {
		return linkToCozinhas(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToCidades(String rel) {
		return linkTo(methodOn(CidadeController.class).listar()).withRel(rel);
	}

	public Link linkToCidades() {
		return linkToCidades(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToEstado(Long id, String rel) {
		return linkTo(methodOn(EstadoController.class).buscar(id)).withRel(rel);
	}

	public Link linkToEstado(Long id) {
		return linkToEstado(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToResponsaveisRestaurante(Long restauranteId) {
		return linkToResponsaveisRestaurante(restauranteId, IanaLinkRelations.SELF.value());
	}

}
