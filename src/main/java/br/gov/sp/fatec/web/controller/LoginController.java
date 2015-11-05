package br.gov.sp.fatec.web.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import liquibase.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.Page;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.service.LoginService;
import br.gov.sp.fatec.service.UsuarioService;

@Component(value = "loginController")
@SessionScoped
public class LoginController implements AccessDeniedHandler {
	private String senhaAtual;
	private String confirmacaoSenha;
	private String novaSenha;
	private boolean senhaAtualOk;
	private boolean novaSenhaOk;
	private boolean confirmacaoSenhaOk;

	@Autowired
	private LoginService service;
	@Autowired
	private UsuarioService usuarioService;

	public Usuario getUsuario() {
		return (Usuario) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
	}

	public void alterarSenha() {
		if (senhaAtualOk && novaSenhaOk && confirmacaoSenhaOk) {
			novaSenha = MD5Util.computeMD5(novaSenha);
			Usuario usuario = getUsuario();
			service.alterarSenha(usuario.getId(), novaSenha);
			senhaAtualOk = false;
			novaSenhaOk = false;
			confirmacaoSenhaOk = false;
			senhaAtual = null;
			novaSenha = null;
			confirmacaoSenha = null;
		}
	}

	public void validarSenhaAtual(FacesContext f, UIComponent c, Object obj) {
		String s = (String) obj;
		Usuario usuario = usuarioService.carregarPorId(getUsuario().getId());
		if (s == null || !MD5Util.computeMD5(s).equals(usuario.getSenha())) {
			senhaAtualOk = false;
			throw new ValidatorException(new FacesMessage(
					"Senha atual não confere!"));
		}
		senhaAtualOk = true;
	}

	public void validarNovaSenha(FacesContext f, UIComponent c, Object obj) {
		String s = (String) obj;
		if (StringUtils.isBlank(s)) {
			novaSenhaOk = false;
			throw new ValidatorException(new FacesMessage(
					"Nova senha é campo obrigatório!"));
		}
		novaSenhaOk = true;
		novaSenha = s;
	}

	public void validarConfirmacaoSenha(FacesContext f, UIComponent c,
			Object obj) {
		String s = (String) obj;
		if (s == null || novaSenha == null || !s.equals(novaSenha)) {
			confirmacaoSenhaOk = false;
			throw new ValidatorException(new FacesMessage(
					"Confirmação senha não confere!"));
		}
		confirmacaoSenhaOk = true;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, AccessDeniedException arg2)
			throws IOException, ServletException {
		response.sendRedirect(request.getContextPath().concat("/")
				.concat(Page.LOGIN));
	}

}
