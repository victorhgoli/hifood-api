package com.hifood.infrastructure.repository.service.storage;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.hifood.domain.exception.StorageException;
import com.hifood.domain.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	@Value("${hifood.storage.local.diretorio-fotos}")
	private Path diretorioFotos;

	@Override
	public void armazenar(NovaFoto foto) {
		Path path = getArquivoPath(foto.getNome());

		try {
			FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(path));

		} catch (Exception e) {
			throw new StorageException("Não foi possivel armazenar o arquivo", e);
		}
	}

	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}

	@Override
	public void remover(String nomeArquivo) {
		Path path = getArquivoPath(nomeArquivo);

		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new StorageException("Não foi possivel excluir o arquivo.", e);
		}
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		Path path = getArquivoPath(nomeArquivo);

		try {
			return Files.newInputStream(path);
		} catch (IOException e) {
			throw new StorageException("Não foi possivel recuperar o arquivo.", e);
		}
	}

}
