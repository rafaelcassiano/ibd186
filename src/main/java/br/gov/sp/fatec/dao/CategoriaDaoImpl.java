package br.gov.sp.fatec.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Categoria;

@Repository
@SuppressWarnings(value = "unchecked")
public class CategoriaDaoImpl implements CategoriaDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Categoria salvar(Categoria categoria) {
		getCurrentSession().merge(categoria);
		return categoria;
	}

	@Override
	public void excluir(Categoria categoria) {
		getCurrentSession().delete(categoria);
	}

	@Override
	public List<Categoria> todos() {
		return getCurrentSession().createCriteria(Categoria.class).list();
	}

	@Override
	public Categoria pesquisarPorId(Long id) {
		return (Categoria) getCurrentSession().get(Categoria.class, id);
	}

	@Override
	public void remover(Long id) {
		Categoria categoria = new Categoria();
		categoria.setId(id);
		getCurrentSession().delete(categoria);
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Categoria carregarPorDescricao(String descricao) {
		return (Categoria) getCurrentSession().createCriteria(Categoria.class)
				.add(Restrictions.eq("descricao", descricao)).setMaxResults(1)
				.uniqueResult();
	}

}
