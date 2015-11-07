package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Permissao;

@Service
public interface PermissaoService {

	public List<Permissao> listar();

	public Permissao carregarPorId(long id);

	public void remover(Long id);

	public List<String> salvar(Permissao permissao);
}
