package com.vhworks.hifoodapi.domain.controller;

import java.util.List;
import java.util.Optional;

import com.vhworks.hifoodapi.domain.exception.EntidadeEmUsoException;
import com.vhworks.hifoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.vhworks.hifoodapi.domain.model.Produto;
import com.vhworks.hifoodapi.domain.repository.ProdutoRepository;
import com.vhworks.hifoodapi.domain.service.CadastroProdutoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CadastroProdutoService cadastroProduto;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Produto> buscar(@PathVariable Long produtoId) {
        Optional<Produto> produto = produtoRepository.findById(produtoId);

        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@RequestBody Produto produto) {
        try {
            produto = cadastroProduto.salvar(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(produto);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<?> atualizar(@PathVariable Long produtoId, @RequestBody Produto produto) {
        try {
            Optional<Produto> produtoAtual = produtoRepository.findById(produtoId);

            if (produtoAtual.isPresent()) {
                BeanUtils.copyProperties(produto, produtoAtual.get(), "id");

                produto = cadastroProduto.salvar(produtoAtual.get());

                return ResponseEntity.ok(produto);
            }

            return ResponseEntity.notFound().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{produtoId}")
    public ResponseEntity<?> excluir(@PathVariable Long produtoId){
        try {
            cadastroProduto.excluir(produtoId);
            return ResponseEntity.noContent().build();
        }  catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}


    }


}
