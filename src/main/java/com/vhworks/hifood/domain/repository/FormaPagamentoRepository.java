package com.vhworks.hifood.domain.repository;

import com.vhworks.hifood.domain.model.FormaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
