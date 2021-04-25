package com.vhworks.hifood.domain.repository;

import com.vhworks.hifood.domain.model.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends  JpaRepository<Estado,Long>{

   
}
