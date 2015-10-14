package br.gov.sp.fatec.web.controller;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import liquibase.util.MD5Util;

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
	private String senhaAtual;
	private String novaSenha;
	private String confirmacaoSenha;

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

	public void alterarSenha() {
		System.out.println(senhaAtual);
		System.out.println(novaSenha);
		System.out.println(confirmacaoSenha);
	}

	public void validarSenhaAtual(ValueChangeEvent event) {
		if (!MD5Util.computeMD5((String) event.getNewValue()).equals(
				usuarioLogado.getSenha())) {
			System.out.println();
		} else {
			System.out.println();
		}
	}

	public void validarConfirmacaoSenha(ValueChangeEvent event) {
		System.out.println(event.getNewValue());
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}
