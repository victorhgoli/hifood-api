package com.vhworks.hifoodapi.domain.repository;

import com.vhworks.hifoodapi.domain.model.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends  JpaRepository<Estado,Long>{

   
}
