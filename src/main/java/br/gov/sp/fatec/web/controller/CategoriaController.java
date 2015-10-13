package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.Page;
import br.gov.sp.fatec.model.Categoria;
import br.gov.sp.fatec.service.CategoriaService;
import br.gov.sp.fatec.web.WebUtils;

@Component(value = "categoriaController")
@RequestScoped
public class CategoriaController {
	private Categoria categoria;
	private List<Categoria> categorias;

	@Autowired
	private CategoriaService service;

	public String iniciar() {
		categorias = service.listar();
		return Page.CATEGORIA_LISTA;
	}

	public String iniciarCadastro() {
		if(categoria != null) {
			categoria.setId(0);
		}
		return Page.CATEGORIA_EDICAO;
	}

	public String carregar(Long id) {
		categoria = service.carregarPorId(id);
		return Page.CATEGORIA_EDICAO;
	}

	public String remover(Long id) {
		service.remover(id);
		return iniciar();
	}

	public String salvar() {
		List<String> messages = service.salvar(categoria);
		if (messages != null && messages.size() > 0) {
			for (String erro : messages) {
				WebUtils.incluirMensagemAviso(erro);
			}
			return Page.CATEGORIA_EDICAO;
		} else {
			return iniciar();
		}
	}

	public Categoria getCategoria() {
		if (categoria == null) {
			categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		if (categorias == null) {
			categorias = new ArrayList<Categoria>();
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
}
