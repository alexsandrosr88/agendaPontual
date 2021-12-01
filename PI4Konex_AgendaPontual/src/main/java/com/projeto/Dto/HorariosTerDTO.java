package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosTerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LocalTime ter;
	
	public HorariosTerDTO() {}

	public HorariosTerDTO(LocalTime ter) {
		this.ter = ter;
	}

	public LocalTime getTer() {
		return ter;
	}

	public void setTer(LocalTime ter) {
		this.ter = ter;
	}	
}
