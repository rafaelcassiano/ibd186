package br.gov.sp.fatec.service;

import javax.transaction.Transactional;

import liquibase.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.dao.UsuarioDaoImpl;
import br.gov.sp.fatec.model.Usuario;

@Service(value="loginService")
@Transactional
public class LoginServiceImpl implements LoginService, UserDetailsService {
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
		dao.alterarSenha(usuarioId, novaSenha);
	}

	@Override
	public UserDetails loadUserByUsername(String usuario)
			throws UsernameNotFoundException {
		if (StringUtils.isBlank(usuario)) {
			return null;
		}
		return dao.carregarPorUsuario(usuario);
	}
}
