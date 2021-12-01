package com.projeto.Dto;

import java.io.Serializable;

import org.apache.tomcat.util.codec.binary.Base64;

public class MelhoresFeedbacksDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String foto;
	private String comentario;
	private String nome;
	
	public MelhoresFeedbacksDTO() {}

	public MelhoresFeedbacksDTO(byte [] foto, String comentario, String nome) {
		this.foto = Base64.encodeBase64String(foto);
		this.comentario = comentario;
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(byte [] foto) {
		this.foto = Base64.encodeBase64String(foto);
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
