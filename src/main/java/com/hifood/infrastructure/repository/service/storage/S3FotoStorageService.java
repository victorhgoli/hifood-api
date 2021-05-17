package com.hifood.infrastructure.repository.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hifood.core.storage.StorageProperties;
import com.hifood.domain.service.FotoStorageService;

public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto foto) {
		try {

			String caminho = getCaminhoArquivo(foto.getNome());

			var objMetadata = new ObjectMetadata();
			objMetadata.setContentType(foto.getContentType());

			var putObjectRequest = new PutObjectRequest(storageProperties.getS3().getBucket(), caminho,
					foto.getInputStream(), objMetadata).withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Nao foi possivel enviar arquivo para amazon s3", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {

			String caminho = getCaminhoArquivo(nomeArquivo);

			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminho);

			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Nao foi possivel enviar arquivo para amazon s3", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		String caminho = getCaminhoArquivo(nomeArquivo);

		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminho);

		return FotoRecuperada.builder().url(url.toString()).build();
	}

	private String getCaminhoArquivo(String nome) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nome);
	}

}
