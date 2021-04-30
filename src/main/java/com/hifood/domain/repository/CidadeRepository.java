package com.hifood.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hifood.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

    
}
