package com.projeto.Dto;

import java.io.Serializable;

public class UltimoIdDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer num;
	
	public UltimoIdDTO() {}

	public UltimoIdDTO(Integer num) {
		this.num = num;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
