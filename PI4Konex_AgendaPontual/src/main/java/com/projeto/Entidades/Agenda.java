package com.projeto.Entidades;

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
@Table(name = "agenda_medica")
public class Agenda {
    
	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idagen")
    private int idAgen;

    @ManyToOne
    @JoinColumn(name = "fk_dia_agen", unique = true)
    private DiasSemana dia;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_hor_agen", unique = true)
    private Horario hora;

    @ManyToOne
    @JoinColumn(name = "fk_med_agen", unique = true)
    private Medico medico;

    // Metodo Construtor
    public Agenda() {

    }
    
    // Metodo Construtor com Atributos
    
	public Agenda(int idAgen, DiasSemana dia, Horario hora, Medico medico) {
		this.idAgen = idAgen;
		this.dia = dia;
		this.hora = hora;
		this.medico = medico;
	}
	// Getters e Setters
	
	public int getIdAgen() {
		return idAgen;
	}

	public void setIdAgen(int idAgen) {
		this.idAgen = idAgen;
	}

	public DiasSemana getDia() {
		return dia;
	}

	public void setDia(DiasSemana dia) {
		this.dia = dia;
	}

	public Horario getHora() {
		return hora;
	}

	public void setHora(Horario hora) {
		this.hora = hora;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}
