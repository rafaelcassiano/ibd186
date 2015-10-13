package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.PAGE;
import br.gov.sp.fatec.model.Usuario;
import br.gov.sp.fatec.service.UsuarioService;
import br.gov.sp.fatec.web.WebUtils;

@Component(value = "usuarioController")
@ViewScoped
public class UsuarioController {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private String confirmacaoSenha;

	@Autowired
	private UsuarioService service;

	public String iniciar() {
		usuarios = service.listar();
		return PAGE.USUARIO_LISTA;
	}

	public String iniciarCadastro() {
		return PAGE.USUARIO_EDICAO;
	}

	public String carregar(Long id) {
		usuario = service.carregarPorId(id);
		return PAGE.USUARIO_EDICAO;
	}
	
	public String remover(Long id) {
		service.remover(id);
		return iniciar();
	}

	public String salvar() {
		List<String> messages = service.salvar(usuario, confirmacaoSenha);
		if (messages != null && messages.size() > 0) {
			for (String erro : messages) {
				WebUtils.incluirMensagemAviso(erro);
			}
			return PAGE.USUARIO_EDICAO;
		} else {
			return iniciar();
		}
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = new ArrayList<Usuario>();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}
