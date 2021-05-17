package com.hifood.infrastructure.repository.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.hifood.core.storage.StorageProperties;
import com.hifood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;

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
		return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
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
	public FotoRecuperada recuperar(String nomeArquivo) {
		Path path = getArquivoPath(nomeArquivo);

		try {
			return FotoRecuperada.builder().inputStream(Files.newInputStream(path)).build();
		} catch (IOException e) {
			throw new StorageException("Não foi possivel recuperar o arquivo.", e);
		}
	}

}
