package br.gov.sp.fatec.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
	private static final long serialVersionUID = 4576500509794375100L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private long id;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "nome")
	private String nome;
	@Column(name = "senha")
	private String senha;
	@ManyToMany
	@JoinTable(name = "usuario__permissoes", joinColumns = @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_usuario__permissao")), inverseJoinColumns = @JoinColumn(name = "permissao_id", foreignKey = @ForeignKey(name = "FK_permissao__usuario")))
	private List<Permissao> permissoes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissoes;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return usuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long[] getPermissoesNum() {
		if (permissoes != null) {
			List<Long> ids = new ArrayList<Long>();
			for (Permissao p : permissoes) {
				ids.add(p.getId());
			}
			return ids.toArray(new Long[ids.size()]);
		} else {
			return new Long[] { 0l };
		}
	}

	public void setPermissoesNum(Long[] ids) {
		if (ids == null || ids.length == 0) {
			permissoes = null;
		} else {
			permissoes = new ArrayList<Permissao>();
			for (Long l : ids) {
				Permissao p = new Permissao();
				p.setId(l);
				permissoes.add(p);
			}
		}
	}
}
