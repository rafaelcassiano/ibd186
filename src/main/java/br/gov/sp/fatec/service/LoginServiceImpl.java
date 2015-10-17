package br.gov.sp.fatec.service;

import javax.transaction.Transactional;

import liquibase.util.MD5Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.dao.UsuarioDaoImpl;
import br.gov.sp.fatec.model.Usuario;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UsuarioDaoImpl dao;

	@Override
	public Usuario validarUsuario(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		Usuario usuarioAux = dao.carregarPorUsuario(usuario.getUsuario());
		if (usuarioAux != null) {
			if (!usuarioAux.getSenha().equals(
					MD5Util.computeMD5(usuario.getSenha()))) {
				usuarioAux = null;
			}
		}
		return usuarioAux;
	}

	@Override
	public void alterarSenha(long usuarioId, String novaSenha) {
		dao.alterarSenha(usuarioId,novaSenha);
	}
}
