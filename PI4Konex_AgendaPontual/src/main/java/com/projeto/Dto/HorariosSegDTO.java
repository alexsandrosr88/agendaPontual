package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosSegDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalTime seg;
	
	public HorariosSegDTO() {}
	
	public HorariosSegDTO(LocalTime seg) {
		this.seg = seg;
	}

	public LocalTime getSeg() {
		return seg;
	}

	public void setSeg(LocalTime seg) {
		this.seg = seg;
	}
}
