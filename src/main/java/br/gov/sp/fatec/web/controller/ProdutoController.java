package br.gov.sp.fatec.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.gov.sp.fatec.constantes.Page;
import br.gov.sp.fatec.model.Categoria;
import br.gov.sp.fatec.model.Produto;
import br.gov.sp.fatec.service.CategoriaService;
import br.gov.sp.fatec.service.ProdutoService;
import br.gov.sp.fatec.web.WebUtils;

@Component(value = "produtoController")
@RequestScoped
public class ProdutoController {
	private Produto produto;
	private List<Produto> produtos;
	private List<Categoria> categorias;

	@Autowired
	private ProdutoService service;
	@Autowired
	private CategoriaService categoriaService;

	public String iniciar() {
		produtos = service.listar();
		return Page.PRODUTO_LISTA;
	}

	public String iniciarCadastro() {
		produto = null;
		categorias = categoriaService.listar();
		return Page.PRODUTO_EDICAO;
	}

	public String carregar(Long id) {
		produto = service.carregarPorId(id);
		categorias = categoriaService.listar();
		return Page.PRODUTO_EDICAO;
	}

	public String remover(Long id) {
		service.remover(id);
		return iniciar();
	}

	public String salvar() {
		List<String> messages = service.salvar(produto);
		if (messages != null && messages.size() > 0) {
			for (String erro : messages) {
				WebUtils.incluirMensagemAviso(erro);
			}
			return Page.PRODUTO_EDICAO;
		} else {
			return iniciar();
		}
	}

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			return new ArrayList<Produto>();
		}
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
