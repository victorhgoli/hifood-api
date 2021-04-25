package com.vhworks.hifoodapi.domain.repository;

import com.vhworks.hifoodapi.domain.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
}
