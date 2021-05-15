package com.hifood.api.model.input;

import javax.validation.constraints.NotBlank;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.hifood.core.validation.FileContentType;
import com.hifood.core.validation.FileSize;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFotoInput {

	@NotNull
	@FileSize(max="500KB")
	@FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;

	@NotBlank
	private String descricao;

}
