package com.projeto.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfeed")
    private Integer idFeed;

    @Column(nullable = true)
    private int avaliacao;
    
    @Column(nullable = true)
    private int pontualidade;

    @Column(nullable = true)
    private String comentario;

    @Column(name = "paramedico", nullable = true)
    private boolean paraMed;

    @Column(name = "paraconsultorio",nullable = true)
    private boolean paraCli;

    @Column(name = "anonimo",nullable = true)
    private boolean feedAno;

    // Metodo Construtor
    public Feedback() {
    }
    // Metodo Construtor com Atributos
	public Feedback(Integer idFeed, int avaliacao, int pontualidade, String comentario, boolean paraMed,
			boolean paraCli, boolean feedAno) {
		this.idFeed = idFeed;
		this.avaliacao = avaliacao;
		this.pontualidade = pontualidade;
		this.comentario = comentario;
		this.paraMed = paraMed;
		this.paraCli = paraCli;
		this.feedAno = feedAno;
	}
	// Getters e Setters
	public Integer getIdFeed() {
		return idFeed;
	}
	public void setIdFeed(Integer idFeed) {
		this.idFeed = idFeed;
	}
	public int getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}
	public int getPontualidade() {
		return pontualidade;
	}
	public void setPontualidade(int pontualidade) {
		this.pontualidade = pontualidade;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public boolean isParaMed() {
		return paraMed;
	}
	public void setParaMed(boolean paraMed) {
		this.paraMed = paraMed;
	}
	public boolean isParaCli() {
		return paraCli;
	}
	public void setParaCli(boolean paraCli) {
		this.paraCli = paraCli;
	}
	public boolean isFeedAno() {
		return feedAno;
	}
	public void setFeedAno(boolean feedAno) {
		this.feedAno = feedAno;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFeed);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		return Objects.equals(idFeed, other.idFeed);
	}
}