package com.hifood.api.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioIdInput {

	@NotNull
	private Long id;
}