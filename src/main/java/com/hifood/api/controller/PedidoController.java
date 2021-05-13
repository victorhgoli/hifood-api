package com.hifood.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hifood.api.assembler.PedidoInputDisassembler;
import com.hifood.api.assembler.PedidoModelAssembler;
import com.hifood.api.assembler.PedidoResumoModelAssembler;
import com.hifood.api.model.PedidoModel;
import com.hifood.api.model.PedidoResumoModel;
import com.hifood.api.model.input.PedidoInput;
import com.hifood.core.data.PageableTranslator;
import com.hifood.domain.exception.EntidadeNaoEncontradaException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.filter.PedidoFilter;
import com.hifood.domain.model.Pedido;
import com.hifood.domain.model.Usuario;
import com.hifood.domain.repository.PedidoRepository;
import com.hifood.domain.service.EmissaoPedidoService;
import com.hifood.infrastructure.repository.specs.PedidoSpecs;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@GetMapping
	public Page<PedidoResumoModel> pesquisar(PedidoFilter filtro,@PageableDefault(size= 5) Pageable pageable) {
		pageable = traduzirPageable(pageable);

		Page<Pedido> pedidosPageable = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageable);
		List<PedidoResumoModel> pedidosResumoModel = pedidoResumoModelAssembler.toCollectionModel(pedidosPageable.getContent());

		Page<PedidoResumoModel> pagePedidoResumo = new PageImpl<>(pedidosResumoModel, pageable, pedidosPageable.getTotalElements());

		return pagePedidoResumo;
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		return pedidoModelAssembler.toModel(emissaoPedido.buscarOufalhar(codigoPedido));
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {

		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitirPedido(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "valorTotal"
				);
		
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
