package br.gov.sp.fatec.web.controller;

import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.PAGE;
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
			return PAGE.LOGIN;
		}
		return PAGE.HOME;
	}

	public String logout() {
		usuarioLogado = null;
		return PAGE.LOGIN;
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
