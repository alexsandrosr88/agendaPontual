package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosQuiDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private LocalTime qui;
	
	public HorariosQuiDTO() {}

	public HorariosQuiDTO(LocalTime qui) {
		this.qui = qui;
	}

	public LocalTime getQui() {
		return qui;
	}

	public void setQui(LocalTime qui) {
		this.qui = qui;
	}
}
