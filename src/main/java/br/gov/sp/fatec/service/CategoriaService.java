package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Categoria;

@Service
public interface CategoriaService {

	public List<Categoria> listar();

	public Categoria carregarPorId(Long id);

	public void remover(Long id);

	public List<String> salvar(Categoria categoria);
	
}
