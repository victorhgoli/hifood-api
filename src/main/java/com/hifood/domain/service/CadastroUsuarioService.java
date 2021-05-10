package com.hifood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.hifood.domain.exception.EntidadeEmUsoException;
import com.hifood.domain.exception.NegocioException;
import com.hifood.domain.exception.UsuarioNaoEncontradaException;
import com.hifood.domain.model.Usuario;
import com.hifood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String MSG_USUARIO_EM_USO = "Usuario de código %d não pode ser removido, pois está em uso";

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(String.format("Já existe um usuario cadastrado com o email %s", usuario.getEmail()));
		}
		
		return usuarioRepository.save(usuario);
	}

	@Transactional
	public void excluir(Long usuarioId) {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradaException(usuarioId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
	}

	@Transactional
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradaException(usuarioId));
	}

	@Transactional
	public void alterarSenha(Long userId, String senhaAtual, String novaSenha) {

		Usuario usuario = buscarOuFalhar(userId);

		if (!usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada nao coincide com a senha do usuario");
		}

		usuario.setSenha(novaSenha);

	}

}
