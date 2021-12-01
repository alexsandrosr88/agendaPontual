package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosQuaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalTime qua;
	
	public HorariosQuaDTO() {}

	public HorariosQuaDTO(LocalTime qua) {
		this.qua = qua;
	}

	public LocalTime getQua() {
		return qua;
	}

	public void setQua(LocalTime qua) {
		this.qua = qua;
	}
}
