package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosSabDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private LocalTime sab;
	
	public HorariosSabDTO() {}

	public HorariosSabDTO(LocalTime sab) {
		this.sab = sab;
	}

	public LocalTime getSab() {
		return sab;
	}

	public void setSab(LocalTime sab) {
		this.sab = sab;
	}	
}
