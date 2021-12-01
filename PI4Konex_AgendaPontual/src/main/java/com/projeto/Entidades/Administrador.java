package com.projeto.Entidades;

import javax.persistence.*;

@Entity
@Table(name = "adm")
public class Administrador {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idadm;

    @Column(nullable = true, unique = true)
    private String nome;
    
    @OneToOne
    @JoinColumn(name = "fk_usu_adm")
    private Usuario usuario;

    // Metodo Construtor
    public Administrador() {

    }
    // Metodo Construtor com Atributos
	public Administrador(int idadm, String nome, Usuario usuario) {
		this.idadm = idadm;
		this.nome = nome;
		this.usuario = usuario;
	}
	// Getters e Setters
	public int getIdadm() {
		return idadm;
	}
	public void setIdadm(int idadm) {
		this.idadm = idadm;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}