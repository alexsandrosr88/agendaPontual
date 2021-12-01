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
import javax.persistence.Table;

@Entity
@Table(name = "ajuda")
public class Ajuda implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idaju")
    private Integer idAjuda;

    @Column(nullable = true, length = 50)
    private String assunto;

    @Column(nullable = true)
    private String mensagem;
    
    @Column(name = "descsolucao", length = 100)
    private String descricaSolucao;

    @ManyToOne
    @JoinColumn(name = "fk_usu_aju")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "fk_adm_aju")
    private Administrador adm;

    // Metodo Construtor
    public Ajuda() {}

    // Metodo Construtor com Atributos
	public Ajuda(Integer idAjuda, String assunto, String mensagem, String descricaSolucao, Usuario usuario,
			Administrador adm) {
		this.idAjuda = idAjuda;
		this.assunto = assunto;
		this.mensagem = mensagem;
		this.descricaSolucao = descricaSolucao;
		this.usuario = usuario;
		this.adm = adm;
	}
	// Getters e Setters
	public Integer getIdAjuda() {
		return idAjuda;
	}

	public void setIdAjuda(Integer idAjuda) {
		this.idAjuda = idAjuda;
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

	public String getDescricaSolucao() {
		return descricaSolucao;
	}

	public void setDescricaSolucao(String descricaSolucao) {
		this.descricaSolucao = descricaSolucao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Administrador getAdm() {
		return adm;
	}

	public void setAdm(Administrador adm) {
		this.adm = adm;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idAjuda);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ajuda other = (Ajuda) obj;
		return Objects.equals(idAjuda, other.idAjuda);
	}
}
