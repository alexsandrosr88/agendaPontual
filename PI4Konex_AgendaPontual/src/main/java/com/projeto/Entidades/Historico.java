package com.projeto.Entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@Table(name = "historico")
public class Historico implements Serializable{
	private static final long serialVersionUID = 1L;

	// Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhis")
    private Integer idHis;

    @Column(nullable = true)
    private String historico;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "histor_consult",
    joinColumns = @JoinColumn(name = "fk_his_cons"),
    inverseJoinColumns = @JoinColumn(name = "fk_cons_his"))
    private Set<Consulta> consulta = new HashSet<>();

    // Metodo Construtor
    public Historico() {

    }
    // Metodo Construtor com Atributos
	public Historico(int idHis, String historico, Set<Consulta> consulta) {
		this.idHis = idHis;
		this.historico = historico;
		this.consulta = consulta;
	}
	
	// Getters e Setters
	public Integer getIdHis() {
		return idHis;
	}
	public void setIdHis(Integer idHis) {
		this.idHis = idHis;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public Set<Consulta> getConsulta() {
		return consulta;
	}
	public void setConsulta(Set<Consulta> consulta) {
		this.consulta = consulta;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idHis);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Historico other = (Historico) obj;
		return idHis == other.idHis;
	}
}
