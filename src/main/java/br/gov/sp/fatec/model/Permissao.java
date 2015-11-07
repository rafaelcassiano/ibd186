package br.gov.sp.fatec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permissao")
public class Permissao implements GrantedAuthority {
	private static final long serialVersionUID = 8815995568016435742L;
	@Id
	@GeneratedValue
	@Column(name = "permissao_id")
	private long id;
	@Column(name = "descricao")
	private String descricao;

	@Override
	public String getAuthority() {
		return "ROLE_".concat(descricao);
	}

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

}
