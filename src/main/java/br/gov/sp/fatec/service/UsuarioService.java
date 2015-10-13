package br.gov.sp.fatec.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Usuario;

@Service
public interface UsuarioService {

	public List<Usuario> listar();

	public Usuario carregarPorId(Long id);

	public List<String> salvar(Usuario usuario, String confirmacaoSenha);

	public void remover(Long id);

}
