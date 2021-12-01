package com.projeto.Dto;

import java.io.Serializable;

public class AvaliacoesPositivasDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer positiva;
	
	public AvaliacoesPositivasDTO() {}

	public AvaliacoesPositivasDTO(Integer positiva) {
		this.positiva = positiva;
	}

	public Integer getPositiva() {
		return positiva;
	}

	public void setPositiva(Integer positiva) {
		this.positiva = positiva;
	}
}
