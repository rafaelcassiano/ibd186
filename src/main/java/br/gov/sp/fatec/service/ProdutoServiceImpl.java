package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.dao.ProdutoDao;
import br.gov.sp.fatec.model.Produto;

@Service
@Transactional
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoDao dao;

	@Override
	public List<Produto> listar() {
		return dao.todos();
	}

	@Override
	public Produto carregarPorId(Long id) {
		return dao.pesquisarPorId(id);
	}

	@Override
	public void remover(Long id) {
		dao.remover(id);
	}

	@Override
	public List<String> salvar(Produto produto) {
		List<String> erros = validar(produto);
		if (erros != null && erros.size() > 0) {
			return erros;
		}
		dao.salvar(produto);
		return null;
	}

	private List<String> validar(Produto produto) {
		List<String> erros = new ArrayList<String>();
		if (StringUtils.isBlank(produto.getDescricao())) {
			erros.add("Descrição é campo obrigatório!");
		} else if (produto.getId() == 0) {
			Produto aux = dao.carregarPorDescricao(produto.getDescricao());
			if (aux != null && aux.getId() > 0) {
				erros.add("Já existe um produto com essa descrição!");
			}
		}
		if (produto.getPreco() <= 0) {
			erros.add("Preço é campo obrigatório!");
		}
		return erros;
	}

}
