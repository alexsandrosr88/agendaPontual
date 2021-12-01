package com.projeto.Dto;

import java.io.Serializable;

public class AvaliacoesNegativasDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer negativa;
	
	public AvaliacoesNegativasDTO() {}

	public AvaliacoesNegativasDTO(Integer negativa) {
		this.negativa = negativa;
	}

	public Integer getNegativa() {
		return negativa;
	}

	public void setNegativa(Integer negativa) {
		this.negativa = negativa;
	}
}
