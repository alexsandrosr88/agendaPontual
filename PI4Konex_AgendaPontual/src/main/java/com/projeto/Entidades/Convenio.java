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
import javax.persistence.Table;


@Entity
@Table(name = "convenio")
public class Convenio implements Serializable{
	private static final long serialVersionUID = 1L;

	// Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idconv")
    private Integer idConv;

    @Column(name = "nome", nullable = true)
    private String nomeConv;

    @Column(name = "valor", nullable = true, columnDefinition = "DECIMAL(5,2) DEFAULT 0.00")
    private float valorConv;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "convenio_medico",
    joinColumns = @JoinColumn(name = "fk_conv_med"),
    inverseJoinColumns = @JoinColumn(name = "fk_med_conv"))
    private List<Medico> medico = new ArrayList<>();

    // Metodo Construtor
    public Convenio() {

    }

    // Metodo Construtor com Atributos
    public Convenio(Integer idConv, String nomeConv, float valorConv) {
        this.idConv = idConv;
        this.nomeConv = nomeConv;
        this.valorConv = valorConv;
    }
    // Getters e Setters
	public Integer getIdConv() {
		return idConv;
	}

	public void setIdConv(Integer idConv) {
		this.idConv = idConv;
	}

	public String getNomeConv() {
		return nomeConv;
	}

	public void setNomeConv(String nomeConv) {
		this.nomeConv = nomeConv;
	}

	public float getValorConv() {
		return valorConv;
	}

	public void setValorConv(float valorConv) {
		this.valorConv = valorConv;
	}

	public List<Medico> getMedico() {
		return medico;
	}

	public void setMedico(List<Medico> medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idConv);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Convenio other = (Convenio) obj;
		return Objects.equals(idConv, other.idConv);
	}
}
