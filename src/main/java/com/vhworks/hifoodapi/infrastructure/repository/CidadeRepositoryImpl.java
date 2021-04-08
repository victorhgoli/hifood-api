package com.vhworks.hifoodapi.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vhworks.hifoodapi.domain.model.Cidade;
import com.vhworks.hifoodapi.domain.repository.CidadeRepository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade", Cidade.class)
				.getResultList();
	}
	
	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Transactional
	@Override
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	@Override
	public void remover(Long cidadeId) {
		Cidade cidade = buscar(cidadeId);

		if(cidade == null){
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cidade);
	}
}
