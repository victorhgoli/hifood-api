package com.hifood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hifood.domain.model.Grupo;

@Repository
public interface GrupoRepository extends  JpaRepository<Grupo,Long>{

   
}
