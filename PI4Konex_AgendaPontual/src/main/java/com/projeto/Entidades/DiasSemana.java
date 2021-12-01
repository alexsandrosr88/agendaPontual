package com.projeto.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "dias_semana")
public class DiasSemana {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iddia")
    private int idDia;

    @Column(nullable = true)
    private String dia;

    // Metodo Construtor
    public DiasSemana() {
    }

    // Metodo Construtor com Atributos
    public DiasSemana(int idDia, String dia) {
        this.idDia = idDia;
        this.dia = dia;
    }

    // Getters e Setters
    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

}
