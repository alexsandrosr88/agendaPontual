package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalTime;

public class HorariosSexDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalTime sex;
	
	public HorariosSexDTO() {}

	public HorariosSexDTO(LocalTime sex) {
		this.sex = sex;
	}

	public LocalTime getSex() {
		return sex;
	}

	public void setSex(LocalTime sex) {
		this.sex = sex;
	}
}
