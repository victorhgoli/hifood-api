package com.hifood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.FormaPagamentoModelAssembler;
import com.hifood.api.assembler.RestauranteInputDisassembler;
import com.hifood.api.assembler.RestauranteModelAssembler;
import com.hifood.api.model.FormaPagamentoModel;
import com.hifood.api.model.RestauranteModel;
import com.hifood.api.model.input.RestauranteInput;
import com.hifood.domain.exception.CidadeNaoEncontradaException;
import com.hifood.domain.exception.CozinhaNaoEncontradaException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.exception.RestauranteNaoEncontradoException;
import com.hifood.domain.model.Restaurante;
import com.hifood.domain.repository.RestauranteRepository;
import com.hifood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;

	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);

		return formaPagamentoModelAssembler.toCollectionModel(restaurante.getFormasPagamento());
	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		cadastroRestaurante.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
