package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.dao.PermissaoDao;
import br.gov.sp.fatec.model.Permissao;

@Service
@Transactional
public class PermissaoServiceImpl implements PermissaoService {

	@Autowired
	private PermissaoDao dao;

	@Override
	public List<Permissao> listar() {
		return dao.listar();
	}

	@Override
	public Permissao carregarPorId(long id) {
		return dao.pesquisarPorId(id);
	}

	@Override
	public void remover(Long id) {
		dao.remover(id);
	}

	@Override
	public List<String> salvar(Permissao permissao) {
		List<String> erros = validar(permissao);
		if (erros != null && erros.size() > 0) {
			return erros;
		}
		dao.salvar(permissao);
		return null;
	}

	private List<String> validar(Permissao permissao) {
		List<String> erros = new ArrayList<String>();
		if (StringUtils.isBlank(permissao.getDescricao())) {
			erros.add("Descrição é campo obrigatório!");
		}
		return erros;
	}
}
