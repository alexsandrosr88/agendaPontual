package com.projeto.Entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "doenca")
public class Doenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddoe")
    private int idDoenca;

    @Column(name = "nome", nullable = true)
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "fk_esp_doe")
    private Especialidade especialiade;

    // Metodo Construtor
    public Doenca() {
    }

    // Metodo Construtor com Atributos
    public Doenca(int idDoenca, String nome, Especialidade especialiade) {
		this.idDoenca = idDoenca;
		this.nome = nome;
		this.especialiade = especialiade;
	}

 // Getters e Setters
	public int getIdDoenca() {
		return idDoenca;
	}

	public void setIdDoenca(int idDoenca) {
		this.idDoenca = idDoenca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Especialidade getEspecialiade() {
		return especialiade;
	}

	public void setEspecialiade(Especialidade especialiade) {
		this.especialiade = especialiade;
	} 
}
