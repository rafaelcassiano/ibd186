package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import liquibase.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.dao.UsuarioDao;
import br.gov.sp.fatec.model.Usuario;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioDao dao;

	@Override
	public List<Usuario> listar() {
		return dao.todos();
	}

	@Override
	public Usuario carregarPorId(Long id) {
		return dao.pesquisarPorId(id);
	}

	@Override
	public List<String> salvar(Usuario usuario, String confirmacaoSenha) {
		List<String> erros = validar(usuario, confirmacaoSenha);
		if (erros != null && erros.size() > 0) {
			return erros;
		}
		usuario.setSenha(MD5Util.computeMD5(usuario.getSenha()));
		dao.salvar(usuario);
		return null;
	}

	private List<String> validar(Usuario usuario, String confirmacaoSenha) {
		List<String> erros = new ArrayList<String>();
		if (StringUtils.isBlank(usuario.getUsuario())) {
			erros.add("Usuário é campo obrigatório!");
		}
		if (StringUtils.isBlank(usuario.getSenha())) {
			erros.add("Senha é campo obrigatório!");
		}
		if (StringUtils.isBlank(usuario.getNome())) {
			erros.add("Nome é campo obrigatório!");
		}
		if (usuario.getId() == 0
				&& StringUtils.isNotBlank(usuario.getUsuario())) {
			Usuario aux = dao.carregarPorUsuario(usuario.getUsuario());
			if (aux != null && aux.getId() > 0) {
				erros.add("Nome de usuário indisponível!");
			}
		}
		if (!usuario.getSenha().equals(confirmacaoSenha)) {
			erros.add("A senha está diferente da confirmação!");
		}
		return erros;
	}

	@Override
	public void remover(Long id) {
		dao.remover(id);
	}

}
