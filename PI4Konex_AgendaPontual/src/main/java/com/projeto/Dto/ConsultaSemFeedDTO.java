package com.projeto.Dto;

import java.io.Serializable;

public class ConsultaSemFeedDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer idCons;
	private String nome;
	
	public ConsultaSemFeedDTO() {}

	public ConsultaSemFeedDTO(Integer idCons, String nome) {
		this.idCons = idCons;
		this.nome = nome;
	}

	public Integer getIdCons() {
		return idCons;
	}

	public void setIdCons(Integer idCons) {
		this.idCons = idCons;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
