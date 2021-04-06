package com.vhworks.hifoodapi.jpa;

import com.vhworks.hifoodapi.HifoodApiApplication;
import com.vhworks.hifoodapi.domain.model.Cozinha;
import com.vhworks.hifoodapi.domain.repository.CozinhaRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaCozinhaMain {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(HifoodApiApplication.class)
				.web(WebApplicationType.NONE).run(args);

		CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

		Cozinha cozinha = cozinhaRepository.buscar(1L);

		System.out.println(cozinha.getNome());
	}

}
