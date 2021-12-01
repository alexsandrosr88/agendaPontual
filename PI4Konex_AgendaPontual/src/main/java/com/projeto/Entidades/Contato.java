package com.projeto.Entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contato_site")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcon")
	private Integer idContato;

	@Column(nullable = false, length = 50)
	@NotNull
	@NotBlank
	private String nome;

	@Column(nullable = false, length = 50)
	@NotNull
	@NotBlank
	private String email;

	@Column(nullable = false, length = 15)
	//@NotNull
	//@NotBlank
	private String telefone;

	@Column(name = "contatowpp", nullable = true)
	private boolean contatoWpp;

	@Column(nullable = true, columnDefinition = "Text")
	@NotNull
	@NotBlank
	private String mensagem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_adm_duv")
	private Administrador admin;

	// Metodo Construtor
	public Contato() {
	}

	public Contato(Integer idContato, @NotNull @NotBlank String nome, @NotNull @NotBlank String email,
			@NotNull @NotBlank String telefone, boolean contatoWpp, @NotNull @NotBlank String mensagem,
			Administrador admin) {
		this.idContato = idContato;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.contatoWpp = contatoWpp;
		this.mensagem = mensagem;
		this.admin = admin;
	}

	// Getters e Setters
	public Integer getIdContato() {
		return idContato;
	}

	public void setIdContato(Integer idContato) {
		this.idContato = idContato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isContatoWpp() {
		return contatoWpp;
	}

	public void setContatoWpp(boolean contatoWpp) {
		this.contatoWpp = contatoWpp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Administrador getAdmin() {
		return admin;
	}

	public void setAdmin(Administrador admin) {
		this.admin = admin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idContato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return idContato == other.idContato;
	}
}
