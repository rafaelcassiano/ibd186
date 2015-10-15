package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Produto;

@Service
public interface ProdutoService {

	public List<Produto> listar();

	public Produto carregarPorId(Long id);

	public void remover(Long id);

	public List<String> salvar(Produto produto);
	
}
