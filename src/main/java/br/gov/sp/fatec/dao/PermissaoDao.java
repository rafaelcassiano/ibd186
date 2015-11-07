package br.gov.sp.fatec.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.gov.sp.fatec.model.Permissao;

@Repository
public interface PermissaoDao {
	public List<Permissao> listar();

	public Permissao pesquisarPorId(long id);

	public void remover(Long id);

	public void salvar(Permissao permissao);
}
