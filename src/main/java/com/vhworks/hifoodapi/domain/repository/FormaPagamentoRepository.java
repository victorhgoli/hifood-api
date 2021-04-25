package com.vhworks.hifoodapi.domain.repository;

import com.vhworks.hifoodapi.domain.model.FormaPagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

}
