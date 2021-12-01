package com.projeto.Entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notificacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idnot")
	private Integer idNot;
	
	@Column(length = 100)
	private String assunto;
	
	@Column(length = 150)
	private String mensagem;
	
	@Column(length = 150)
	private String resposta;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_cons_not")
	private Consulta consulta;
	
	public Notificacao() {}
	
	public Notificacao(Integer idNot, String assunto, String mensagem, String resposta, Consulta consulta) {
		this.idNot = idNot;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.resposta = resposta;
		this.consulta = consulta;
	}
	
	public Integer getIdNot() {
		return idNot;
	}

	public void setIdNot(Integer idNot) {
		this.idNot = idNot;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNot);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notificacao other = (Notificacao) obj;
		return Objects.equals(idNot, other.idNot);
	}	
}
