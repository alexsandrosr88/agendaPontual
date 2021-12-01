package com.projeto.Entidades;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
@Table(name = "horario")
public class Horario {
    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhor")
    private int idHora;

    @Column(nullable = true)
    private LocalTime horario;
    
    // Metodo Construtor
    public Horario() {
    }

	public Horario(int idHora, LocalTime horario) {
		this.idHora = idHora;
		this.horario = horario;
	}
	// Metodo Construtor com Atributos
	public int getIdHora() {
		return idHora;
	}

	public void setIdHora(int idHora) {
		this.idHora = idHora;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
}
