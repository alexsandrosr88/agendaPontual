package com.projeto.Entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "triagem")
public class Triagem {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idtri")
    private Integer idTria;

    @ManyToOne
    @JoinColumn(name = "fk_doe_paci")
    private Doenca doenca;

    @ManyToOne
    @JoinColumn(name = "fk_paci_doe")
    private Paciente paciente;

    public Triagem() {
    }

    public Triagem(Integer idTria, Doenca doenca, Paciente paciente) {
        this.idTria = idTria;
        this.doenca = doenca;
        this.paciente = paciente;
    }

    public Integer getIdTria() {
        return idTria;
    }

    public void setIdTria(Integer idTria) {
        this.idTria = idTria;
    }

    public Doenca getDoenca() {
        return doenca;
    }

    public void setDoenca(Doenca doenca) {
        this.doenca = doenca;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
}
