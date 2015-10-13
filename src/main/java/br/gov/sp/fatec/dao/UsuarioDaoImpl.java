package br.gov.sp.fatec.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Usuario;

@Repository
@SuppressWarnings(value = "unchecked")
public class UsuarioDaoImpl implements UsuarioDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Usuario salvar(Usuario usuario) {
		getCurrentSession().merge(usuario);
		return usuario;
	}

	@Override
	public Usuario pesquisarPorId(Long id) {
		return (Usuario) getCurrentSession().get(Usuario.class, id);
	}

	@Override
	public void excluir(Usuario usuario) {
		getCurrentSession().delete(usuario);
	}

	@Override
	public List<Usuario> todos() {
		return getCurrentSession().createCriteria(Usuario.class).list();
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Usuario carregarPorUsuario(String usuario) {
		Criteria crit = getCurrentSession().createCriteria(Usuario.class);
		crit.add(Restrictions.eq("usuario", usuario));
		return (Usuario) crit.uniqueResult();
	}

	@Override
	public void remover(Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		getCurrentSession().delete(usuario);
	}
}