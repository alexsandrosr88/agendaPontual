package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import org.apache.tomcat.util.codec.binary.Base64;

public class FeedbackCliMedDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idFeed;
	private String foto;
	private String comentario;
	private Integer pontos;
	private String nome;
	private String nomeMed;
    private LocalDate dtAgendada;
    private LocalTime hora;
	
	public FeedbackCliMedDTO() {}

	public FeedbackCliMedDTO(Integer idFeed, byte [] foto, String comentario, Integer pontos, String nome,LocalDate dtAgendada, LocalTime hora) {
		this.idFeed = idFeed;
		this.foto = Base64.encodeBase64String(foto);
		this.pontos = pontos;
		this.comentario = comentario;
		this.nome = nome;
        this.dtAgendada = dtAgendada;
        this.hora = hora;
	}

	public FeedbackCliMedDTO(Integer idFeed,  byte [] foto, String comentario, Integer pontos, String nome, String nomeMed,
			LocalDate dtAgendada, LocalTime hora) {
		this.idFeed = idFeed;
		this.foto = Base64.encodeBase64String(foto);
		this.pontos = pontos;
		this.comentario = comentario;
		this.nome = nome;
		this.nomeMed = nomeMed;
		this.dtAgendada = dtAgendada;
		this.hora = hora;
	}

	public Integer getIdFeed() {
		return idFeed;
	}

	public void setIdFeed(Integer idFeed) {
		this.idFeed = idFeed;
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

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeMed() {
		return nomeMed;
	}

	public void setNomeMed(String nomeMed) {
		this.nomeMed = nomeMed;
	}

    public LocalDate getDtAgendada() {
        return dtAgendada;
    }

    public void setDtAgendada(LocalDate dtAgendada) {
        this.dtAgendada = dtAgendada;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    
}
