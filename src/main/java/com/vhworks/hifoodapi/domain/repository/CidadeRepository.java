package com.vhworks.hifoodapi.domain.repository;

import com.vhworks.hifoodapi.domain.model.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

    
}
