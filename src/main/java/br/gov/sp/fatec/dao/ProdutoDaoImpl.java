package br.gov.sp.fatec.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Produto;

@Repository
@SuppressWarnings(value = "unchecked")
public class ProdutoDaoImpl implements ProdutoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Produto> todos() {
		return getCurrentSession().createCriteria(Produto.class).list();
	}

	@Override
	public Produto pesquisarPorId(Long id) {
		return (Produto) getCurrentSession().get(Produto.class, id);
	}

	@Override
	public void remover(Long id) {
		Produto produto = new Produto();
		produto.setId(id);
		getCurrentSession().delete(produto);
	}

	@Override
	public Produto carregarPorDescricao(String descricao) {
		return (Produto) getCurrentSession().createCriteria(Produto.class)
				.add(Restrictions.eq("descricao", descricao)).uniqueResult();
	}

	@Override
	public void salvar(Produto produto) {
		getCurrentSession().merge(produto);
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
