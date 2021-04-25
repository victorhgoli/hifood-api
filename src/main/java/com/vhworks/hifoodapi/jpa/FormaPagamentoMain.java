package com.vhworks.hifoodapi.jpa;

import com.vhworks.hifoodapi.HifoodApiApplication;
import com.vhworks.hifoodapi.domain.model.FormaPagamento;
import com.vhworks.hifoodapi.domain.repository.FormaPagamentoRepository;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FormaPagamentoMain {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(HifoodApiApplication.class).web(WebApplicationType.NONE).run(args);

        FormaPagamentoRepository formaPagamentoRepository = context.getBean(FormaPagamentoRepository.class);


        FormaPagamento dinheiro = new FormaPagamento();
        dinheiro.setDescricao("dinheiro");

        FormaPagamento cartao = new FormaPagamento();
        cartao.setDescricao("Cartão Visa");


        formaPagamentoRepository.save(dinheiro);
        formaPagamentoRepository.save(cartao);

        



    }

    
}
