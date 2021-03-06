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
import com.hifood.api.controller.EstatisticasController;
import com.hifood.api.controller.FluxoPedidoController;
import com.hifood.api.controller.FormaPagamentoController;
import com.hifood.api.controller.GrupoController;
import com.hifood.api.controller.GrupoPermissaoController;
import com.hifood.api.controller.PedidoController;
import com.hifood.api.controller.PermissaoController;
import com.hifood.api.controller.RestauranteController;
import com.hifood.api.controller.RestauranteFormaPagamentoController;
import com.hifood.api.controller.RestauranteProdutoController;
import com.hifood.api.controller.RestauranteProdutoFotoController;
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
		return linkToPedidos(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToPedidos(String rel) {
		var fitrosVariables = new TemplateVariables(new TemplateVariable("clientId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));

		var pedidosUrl = linkTo(PedidoController.class).toUri().toString();

		return Link.of(UriTemplate.of(pedidosUrl, PAGINACAO_VARIABLES.concat(fitrosVariables)), rel);
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

	public Link linkToCozinha(Long id, String rel) {
		return linkTo(methodOn(CozinhaController.class).buscar(id)).withRel(rel);
	}

	public Link linkToCozinha(Long id) {
		return linkToCozinha(id, IanaLinkRelations.SELF_VALUE.toString());
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

	public Link linkToConfirmacaoPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).confirmar(codigo)).withRel(rel);
	}

	public Link linkToEntregaPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).entregar(codigo)).withRel(rel);
	}

	public Link linkToCancelamentoPedido(String codigo, String rel) {
		return linkTo(methodOn(FluxoPedidoController.class).cancelar(codigo)).withRel(rel);
	}

	public Link linkToRestaurantes(String rel) {
		return linkTo(methodOn(RestauranteController.class).listar()).withRel(rel);
	}

	public Link linkToRestaurantes() {
		return linkToRestaurantes(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToRestauranteFormaPagamento(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteFormaPagamento(Long restauranteId) {
		return linkToRestauranteFormaPagamento(restauranteId, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToRestauranteUsuarioResponsavel(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).listar(restauranteId)).withRel(rel);
	}

	public Link linkToRestauranteUsuarioResponsavel(Long restauranteId) {
		return linkToRestauranteUsuarioResponsavel(restauranteId, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToFechamentoRestaurante(Long id, String rel) {
		return linkTo(methodOn(RestauranteController.class).fechar(id)).withRel(rel);
	}

	public Link linkToFechamentoRestaurante(Long id) {
		return linkToFechamentoRestaurante(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToInativacaoRestaurante(Long id, String rel) {
		return linkTo(methodOn(RestauranteController.class).inativar(id)).withRel(rel);
	}

	public Link linkToAberturaRestaurante(Long id, String rel) {
		return linkTo(methodOn(RestauranteController.class).abrir(id)).withRel(rel);
	}

	public Link linkToAberturaRestaurante(Long id) {
		return linkToAberturaRestaurante(id, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToAtivacaoRestaurante(Long id, String rel) {
		return linkTo(methodOn(RestauranteController.class).ativar(id)).withRel(rel);
	}

	public Link linkToFormasPagamento(String rel) {
		return linkTo(methodOn(FormaPagamentoController.class).listar()).withRel(rel);
	}

	public Link linkToFormasPagamento() {
		return linkToFormasPagamento(IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToRestauranteFormaPagamentoDesassociacao(Long restauranteId, Long formaPagamentoId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).desassociar(restauranteId, formaPagamentoId))
				.withRel(rel);
	}

	public Link linkToRestauranteFormaPagamentoAssociacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteFormaPagamentoController.class).associar(restauranteId, null)).withRel(rel);
	}

	public Link linkToRestauranteUsuarioResponsavelDesassociacao(Long restauranteId, Long usuarioId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).desassociar(restauranteId, usuarioId))
				.withRel(rel);
	}

	public Link linkToRestauranteUsuarioResponsavelAssociacao(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteUsuarioResponsavelController.class).associar(restauranteId, null))
				.withRel(rel);
	}

	public Link linkToProdutos(Long restauranteId, String rel) {
		return linkTo(methodOn(RestauranteProdutoController.class).listar(restauranteId, null)).withRel(rel);
	}

	public Link linkToProdutos(Long restauranteId) {
		return linkToProdutos(restauranteId, IanaLinkRelations.SELF_VALUE.toString());
	}

	public Link linkToFotoProduto(Long restauranteId, Long produtoId, String rel) {
		return linkTo(methodOn(RestauranteProdutoFotoController.class).buscar(restauranteId, produtoId)).withRel(rel);
	}

	public Link linkToFotoProduto(Long restauranteId, Long produtoId) {
		return linkToFotoProduto(restauranteId, produtoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupos(String rel) {
		return linkTo(methodOn(GrupoController.class).listar()).withRel(rel);
	}

	public Link linkToGrupoPermissoes(Long id, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).listar(id)).withRel(rel);
	}

	public Link linkToPermissoes(String rel) {
		return linkTo(PermissaoController.class).withRel(rel);
	}

	public Link linkToPermissoes() {
		return linkToPermissoes(IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissoes(Long grupoId) {
		return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF.value());
	}

	public Link linkToGrupoPermissaoAssociacao(Long grupoId, String rel) {
		return linkTo(methodOn(GrupoPermissaoController.class).associar(grupoId, null)).withRel(rel);
	}

	public Link linkToUsuarioGrupoAssociacao(Long usuarioId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).associar(usuarioId, null)).withRel(rel);
	}

	public Link linkToUsuarioGrupoDesassociacao(Long usuarioId, Long grupoId, String rel) {
		return linkTo(methodOn(UsuarioGrupoController.class).desassociar(usuarioId, grupoId)).withRel(rel);
	}

	public Link linkToEstatisticas(String rel) {
		return linkTo(methodOn(EstatisticasController.class).estatisticas()).withRel(rel);
	}

	public Link linktoEstatisticaVendaDiaria(String rel) {
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM),
				new TemplateVariable("timeOffset", VariableType.REQUEST_PARAM));

		String pedidosUrl = linkTo(methodOn(EstatisticasController.class).consultarVendasDiarias(null, null)).toUri()
				.toString();

		return Link.of(UriTemplate.of(pedidosUrl, filtroVariables), rel);
	}

}
