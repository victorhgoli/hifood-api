package com.hifood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.GrupoModelAssembler;
import com.hifood.api.model.GrupoModel;
import com.hifood.domain.model.Usuario;
import com.hifood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

	/*
	 * @Autowired private GrupoRepository grupoRepository;
	 * 
	 * @Autowired private CadastroRestauranteService cadastroRestaurante;
	 */
	@Autowired
	private CadastroUsuarioService cadastroUsuario;

	@Autowired
	private GrupoModelAssembler grupoModelAssembler;

	@GetMapping
	public List<GrupoModel> listar(@PathVariable Long usuarioId) {

		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);

		return grupoModelAssembler.toCollectionModel(usuario.getGrupos());
	}
	
	@PutMapping("{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.associarGrupo(usuarioId, grupoId);
	}
	
	@DeleteMapping("{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		cadastroUsuario.desassociarGrupo(usuarioId, grupoId);
	}
	
	/*
	 * @GetMapping("/{produtoId}") public ProdutoModel buscar(@PathVariable Long
	 * restauranteId, @PathVariable Long produtoId) { Produto produto =
	 * cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
	 * 
	 * return produtoModelAssembler.toModel(produto); }
	 * 
	 * @PostMapping
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public ProdutoModel
	 * adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput
	 * produtoInput) { Restaurante restaurante =
	 * cadastroRestaurante.buscarOuFalhar(restauranteId);
	 * 
	 * Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);
	 * 
	 * produto.setRestaurante(restaurante);
	 * 
	 * return produtoModelAssembler.toModel(cadastroProduto.salvar(produto)); }
	 * 
	 * @PutMapping("/{produtoId}") public ProdutoModel atualizar(@PathVariable Long
	 * restauranteId, @PathVariable Long produtoId,
	 * 
	 * @RequestBody @Valid ProdutoInput produtoInput) { Produto produtoAtual =
	 * cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
	 * 
	 * produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);
	 * 
	 * produtoAtual = cadastroProduto.salvar(produtoAtual);
	 * 
	 * return produtoModelAssembler.toModel(produtoAtual); }
	 */

}
