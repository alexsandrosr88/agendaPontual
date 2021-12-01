package com.projeto.Dto;

import java.io.Serializable;

public class AutoCompleteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String label;
	private String value;
	
	public AutoCompleteDTO() {}
	
	public AutoCompleteDTO(String label, String value) {
		this.label = label;
		this.value = value;
	}
	
	public AutoCompleteDTO(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
