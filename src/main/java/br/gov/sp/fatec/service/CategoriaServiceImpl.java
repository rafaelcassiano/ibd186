package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.dao.CategoriaDao;
import br.gov.sp.fatec.model.Categoria;

@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaDao dao;

	@Override
	public List<Categoria> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria carregarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> salvar(Categoria categoria) {
		List<String> erros = validar(categoria);
		if (erros != null && erros.size() > 0) {
			return erros;
		}
		dao.salvar(categoria);
		return null;
	}

	private List<String> validar(Categoria categoria) {
		List<String> erros = new ArrayList<String>();
		if (StringUtils.isBlank(categoria.getDescricao())) {
			erros.add("Descrição é campo obrigatório!");
		} else if (categoria.getId() == 0) {
			Categoria catAux = dao.carregarPorDescricao(categoria
					.getDescricao());
			if (catAux != null && catAux.getId() > 0) {
				erros.add("Já existe uma categoria com essa descrição!");
			}
		}
		return erros;
	}
}
