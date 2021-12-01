package com.projeto.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "bairro")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idbai")
	private int idBai;

	@Column(nullable = true)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "fk_cid_bai")
	private Cidade cidade;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bairro")
	private List<Endereco> endereco = new ArrayList<>();

	public Bairro() {
	}

	public Bairro(int idBai, String nome) {
		this.idBai = idBai;
		this.nome = nome;
	}

//Getters e Setters
	public int getIdBai() {
		return idBai;
	}

	public void setIdBai(int idBai) {
		this.idBai = idBai;
	}

	public String getNome() {
		return nome;
	}
	

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<Endereco> endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idBai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bairro other = (Bairro) obj;
		return idBai == other.idBai;
	}
}
