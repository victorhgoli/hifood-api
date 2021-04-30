package com.hifood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hifood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
