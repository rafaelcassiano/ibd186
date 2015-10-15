package br.gov.sp.fatec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Produto;

@Repository
public interface ProdutoDao {

	public List<Produto> todos();

	public Produto pesquisarPorId(Long id);

	public void remover(Long id);

	public Produto carregarPorDescricao(String descricao);

	public void salvar(Produto produto);
	
	
}
