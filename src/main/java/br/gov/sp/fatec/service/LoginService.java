package br.gov.sp.fatec.service;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.model.Usuario;

@Service
public interface LoginService {
	public Usuario validarUsuario(Usuario usuario);
}
