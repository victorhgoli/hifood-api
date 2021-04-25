package com.vhworks.hifood.domain.repository;

import com.vhworks.hifood.domain.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long>{
}
