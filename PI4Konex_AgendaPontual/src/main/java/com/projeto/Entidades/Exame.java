package com.projeto.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Exame implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idexa")
    private Integer idExame;

    @Column(name = "nome", nullable = true)
    private String nomeExame;

    @Column(name = "codamb92")
    private String codAmbExame;

    @Column(name = "tuss")
    private Integer tussExame;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "exame_consulta",
    joinColumns = @JoinColumn(name = "fk_exame_cons"),
    inverseJoinColumns = @JoinColumn(name = "fk_cons_exame"))
    private List<Consulta> consulta = new ArrayList<>();

    // Metodo Construtor
    public Exame() {}
    
    // Metodo Construtor com Atributos
	public Exame(Integer idExame, String nomeExame, String codAmbExame, Integer tussExame) {
		this.idExame = idExame;
		this.nomeExame = nomeExame;
		this.codAmbExame = codAmbExame;
		this.tussExame = tussExame;
	}
	// Getters e Setters
	public Integer getIdExame() {
		return idExame;
	}

	public void setIdExame(Integer idExame) {
		this.idExame = idExame;
	}

	public String getNomeExame() {
		return nomeExame;
	}

	public void setNomeExame(String nomeExame) {
		this.nomeExame = nomeExame;
	}

	public String getCodAmbExame() {
		return codAmbExame;
	}

	public void setCodAmbExame(String codAmbExame) {
		this.codAmbExame = codAmbExame;
	}

	public Integer getTussExame() {
		return tussExame;
	}

	public void setTussExame(Integer tussExame) {
		this.tussExame = tussExame;
	}

	public List<Consulta> getConsulta() {
		return consulta;
	}

	public void setConsulta(List<Consulta> consulta) {
		this.consulta = consulta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idExame);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		return Objects.equals(idExame, other.idExame);
	}
}
