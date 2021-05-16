package com.hifood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {
	
	InputStream recuperar(String nomeArquivo);
	
	void armazenar(NovaFoto foto);

	void remover(String nomeArquivo);

	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}

	@Getter
	@Builder
	class NovaFoto {
		private String nome;
		private InputStream inputStream;
	}

	default void substituir(String nomeArquivoExistente, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if (nomeArquivoExistente != null) {
			this.remover(nomeArquivoExistente);
		}
	}

}
