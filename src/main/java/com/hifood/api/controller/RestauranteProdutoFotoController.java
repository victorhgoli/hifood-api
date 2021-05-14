package com.hifood.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.model.input.ProdutoFotoInput;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {


	@GetMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId,@Valid ProdutoFotoInput fotoInput) {
		
		var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoInput.getArquivo().getOriginalFilename();
		
		var arquivoFoto = Path.of("/Users/victo/Desktop/caralogo", nomeArquivo);
		
		System.out.println(arquivoFoto);
		
		try {
			fotoInput.getArquivo().transferTo(arquivoFoto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
