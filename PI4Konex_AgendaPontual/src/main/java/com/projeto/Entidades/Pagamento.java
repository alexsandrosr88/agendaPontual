package com.projeto.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@Table(name = "pagamento")
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idpag")
    private int idPag;

    @Column(name = "dtpgto", nullable = true, columnDefinition = "DATE")
    private String dataPag;

    @Column(name = "formapagamento", nullable = true, length = 30)
    private String formaPag;

    @Column(columnDefinition = "DECIMAL(6,2) DEFAULT 0.00")
    private float valor;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pag_plan",
    joinColumns = @JoinColumn(name = "fk_pag_plan"),
    inverseJoinColumns = @JoinColumn(name = "fk_plan_pag"))
    private List<Planos> planos = new ArrayList<>();

    // Metodo Construtor
    public Pagamento() {

    }

    // Metodo Construtor com Atributos
    public Pagamento(int idPag, String dataPag, String formaPag, float valor) {
        this.idPag = idPag;
        this.dataPag = dataPag;
        this.formaPag = formaPag;
        this.valor = valor;
    }

    // Getters e Setters
    public int getIdPag() {
        return idPag;
    }

    public void setIdPag(int idPag) {
        this.idPag = idPag;
    }

    public String getDataPag() {
        return dataPag;
    }

    public void setDataPag(String dataPag) {
        this.dataPag = dataPag;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
