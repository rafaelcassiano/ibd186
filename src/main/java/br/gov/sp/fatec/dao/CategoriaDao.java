package br.gov.sp.fatec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Categoria;

@Repository
public interface CategoriaDao {

	public Categoria salvar(Categoria categoria);

	public void excluir(Categoria categoria);

	public List<Categoria> todos();

	public Categoria pesquisarPorId(Long id);

	public void remover(Long id);

	public Categoria carregarPorDescricao(String descricao);
}
