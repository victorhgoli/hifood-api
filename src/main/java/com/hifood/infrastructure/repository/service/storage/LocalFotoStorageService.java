package com.hifood.infrastructure.repository.service.storage;

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
			throw new StorageException("Não foi possivel armazenar o arquivo",e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}

}
