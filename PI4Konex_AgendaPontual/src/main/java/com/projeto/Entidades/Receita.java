package com.projeto.Entidades;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "receita")
public class Receita {

    // Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrece")
    private int idRec;

    @Column(name = "datacriacao", columnDefinition = "DATE")
    private LocalDateTime dataRec;

    @Column(name = "informacoes", nullable = true)
    private String infoRec;

    // Metodo Construtor
    public Receita() {
    }

    // Metodo Construtor com Atributos
    public Receita(int idRec, LocalDateTime dataRec, String infoRec) {
        this.idRec = idRec;
        this.dataRec = dataRec;
        this.infoRec = infoRec;
    }

    // Getters e Setters
    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public LocalDateTime getDataRec() {
        return dataRec;
    }

    public void setDataRec(LocalDateTime dataRec) {
        this.dataRec = dataRec;
    }

    public String getInfoRec() {
        return infoRec;
    }

    public void setInfoRec(String infoRec) {
        this.infoRec = infoRec;
    }

}
