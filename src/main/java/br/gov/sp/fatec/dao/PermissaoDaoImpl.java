package br.gov.sp.fatec.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Permissao;

@Repository
@SuppressWarnings(value = "unchecked")
public class PermissaoDaoImpl implements PermissaoDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Permissao> listar() {
		return getCurrentSession().createCriteria(Permissao.class).list();
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Permissao pesquisarPorId(long id) {
		return (Permissao) getCurrentSession().get(Permissao.class, id);
	}

	@Override
	public void remover(Long id) {
		Permissao permissao = new Permissao();
		permissao.setId(id);
		getCurrentSession().delete(permissao);
	}

	@Override
	public void salvar(Permissao permissao) {
		getCurrentSession().saveOrUpdate(permissao);
	}

}
