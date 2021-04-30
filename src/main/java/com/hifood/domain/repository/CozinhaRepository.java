package com.hifood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hifood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha,Long>{
	
}
