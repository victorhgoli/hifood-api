package com.hifood.api.model.input;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import com.hifood.core.validation.FileSize;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFotoInput {

	@NotNull
	@FileSize(max="10KB")
	private MultipartFile arquivo;

	@NotBlank
	private String descricao;

}
