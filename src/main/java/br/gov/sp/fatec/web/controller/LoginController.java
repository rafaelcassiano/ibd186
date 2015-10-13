package br.gov.sp.fatec.web.controller;

import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.Page;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.service.LoginService;
import br.gov.sp.fatec.web.WebUtils;

@Component(value = "loginController")
@SessionScoped
public class LoginController {
	private Usuario usuarioLogado;

	@Autowired
	private LoginService service;

	public String login() {
		usuarioLogado = service.validarUsuario(usuarioLogado);
		if (usuarioLogado == null || usuarioLogado.getId() == 0) {
			WebUtils.incluirMensagemErro("Usuário ou Senha inválidos!");
			return Page.LOGIN;
		}
		return Page.HOME;
	}

	public String logout() {
		usuarioLogado = null;
		return Page.LOGIN;
	}

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = new Usuario();
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
