package com.hifood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hifood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query("from Pedido p join fetch p.cliente c join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	

}
