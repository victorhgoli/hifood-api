package com.hifood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class FotoProduto {

	@EqualsAndHashCode.Include
	@Id
	@Column(name = "produtoId")
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	private String descricao;
	private String nomeArquivo;
	private String contentType;
	private Long tamanho;
	
	public Long getRestauranteId() {
		return getProduto().getRestaurante().getId();
	}
}
