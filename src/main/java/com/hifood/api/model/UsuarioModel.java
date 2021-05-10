package com.hifood.api.model;

import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.hifood.domain.model.Grupo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioModel {

	private Long id;

	private String nome;

	private String email;
}
