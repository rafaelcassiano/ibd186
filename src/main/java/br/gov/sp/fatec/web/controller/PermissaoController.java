package br.gov.sp.fatec.web.controller;

import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.Page;
import br.gov.sp.fatec.model.Permissao;
import br.gov.sp.fatec.service.PermissaoService;
import br.gov.sp.fatec.web.WebUtils;

@Component(value = "permissaoController")
@RequestScoped
public class PermissaoController {
	private Permissao permissao;
	private List<Permissao> permissoes;

	@Autowired
	private PermissaoService service;

	public String iniciar() {
		setPermissoes(service.listar());
		return Page.PERMISSAO_LISTA;
	}

	public String iniciarCadastro() {
		permissao = null;
		return Page.PERMISSAO_EDICAO;
	}

	public String carregar(Long id) {
		permissao = service.carregarPorId(id);
		return Page.PERMISSAO_EDICAO;
	}

	public String salvar() {
		List<String> messages = service.salvar(permissao);
		if (messages != null && messages.size() > 0) {
			for (String erro : messages) {
				WebUtils.incluirMensagemAviso(erro);
			}
			return Page.PERMISSAO_EDICAO;
		} else {
			return iniciar();
		}
	}

	public String remover(Long id) {
		service.remover(id);
		return iniciar();
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	public Permissao getPermissao() {
		if (permissao == null) {
			permissao = new Permissao();
		}
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}
}
