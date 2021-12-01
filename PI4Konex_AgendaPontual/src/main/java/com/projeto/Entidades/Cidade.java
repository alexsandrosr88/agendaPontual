package com.projeto.Entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="cidade")
public class Cidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcid")
    private int idCid;

    @Column(nullable = true)
    private String nome;
    
    @OneToMany(mappedBy = "cidade")
    private Set<Bairro> bairros;
    
    
    //Metodo Construtor
    public Cidade() {
    }

	public Cidade(int idCid, String nome) {
		this.idCid = idCid;
		this.nome = nome;
	}
	
	// Getters e Setters
	public int getIdCid() {
		return idCid;
	}

	public void setIdCid(int idCid) {
		this.idCid = idCid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idCid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cidade other = (Cidade) obj;
		return idCid == other.idCid;
	}
}
