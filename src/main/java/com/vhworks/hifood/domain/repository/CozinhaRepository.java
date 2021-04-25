package com.vhworks.hifood.domain.repository;

import com.vhworks.hifood.domain.model.Cozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long>{
	
}
