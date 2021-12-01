package com.projeto.Dto;

import java.io.Serializable;

public class QuantidadeAtendimentosDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String quantidade;
	
	public QuantidadeAtendimentosDTO() {}

	public QuantidadeAtendimentosDTO(String quantidade) {
		this.quantidade = quantidade;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
}
