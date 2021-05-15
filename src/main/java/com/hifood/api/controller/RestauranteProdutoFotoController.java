package com.hifood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hifood.api.assembler.FotoProdutoModelAssembler;
import com.hifood.api.model.FotoProdutoModel;
import com.hifood.api.model.input.ProdutoFotoInput;
import com.hifood.domain.model.FotoProduto;
import com.hifood.domain.model.Produto;
import com.hifood.domain.service.CadastroProdutoService;
import com.hifood.domain.service.CatalogoFotoProdutoService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private CatalogoFotoProdutoService catalogoProduto;
	
	@Autowired
	private CadastroProdutoService cadastroProduto;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;


	@GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId,@Valid ProdutoFotoInput fotoInput) {
		
		Produto produto = cadastroProduto.buscarOuFalhar(restauranteId, produtoId);
		MultipartFile arquivo = fotoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setContentType(arquivo.getContentType());
		foto.setDescricao(fotoInput.getDescricao());
		foto.setProduto(produto);
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		return fotoProdutoModelAssembler.toModel(catalogoProduto.salvar(foto));
		
		/*
		 * var nomeArquivo = UUID.randomUUID().toString() + "_" +
		 * fotoInput.getArquivo().getOriginalFilename();
		 * 
		 * var arquivoFoto = Path.of("/Users/victo/Desktop/caralogo", nomeArquivo);
		 * 
		 * System.out.println(arquivoFoto);
		 * 
		 * try { fotoInput.getArquivo().transferTo(arquivoFoto); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		
	}

}
