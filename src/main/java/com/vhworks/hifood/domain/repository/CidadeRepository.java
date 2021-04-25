package com.vhworks.hifood.domain.repository;


import com.vhworks.hifood.domain.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

    
}
