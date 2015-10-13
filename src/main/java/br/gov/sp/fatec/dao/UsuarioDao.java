package br.gov.sp.fatec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Usuario;

@Repository
public interface UsuarioDao {
	public Usuario carregarPorUsuario(String usuario);

	public Usuario salvar(Usuario usuario);

	public void excluir(Usuario usuario);

	public List<Usuario> todos();

	public Usuario pesquisarPorId(Long id);

	public void remover(Long id);
}
