package com.vhworks.hifood.domain.repository;

import com.vhworks.hifood.domain.model.Permissao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao,Long>{

    
}
