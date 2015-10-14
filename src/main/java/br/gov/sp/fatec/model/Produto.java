package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "produto_id")
	private long id;
	@Column(name = "descricao")
	private String descricao;
	@Column(name = "preco")
	private double preco;
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	@Column(name = "fg_disponivel")
	private boolean fgDisponivel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
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

	public boolean isFgDisponivel() {
		return fgDisponivel;
	}

	public void setFgDisponivel(boolean fgDisponivel) {
		this.fgDisponivel = fgDisponivel;
	}

	public String getDisponivel() {
		if (fgDisponivel) {
			return "Sim";
		} else {
			return "Não";
		}
	}

}
