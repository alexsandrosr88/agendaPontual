package com.projeto.Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paciente")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpaci")
    private Integer idPaci;

    @Column(name = "nome", nullable = true)
    private String nomePaci;

    @Column(nullable = true, unique = true)
    private String cpf;

    @Column(name = "datanasci", nullable = true, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNasc;

    @Column(nullable = true, unique = true)
    private String rg;

    @Column(nullable = true, unique = true)
    private String celular;

    @Column(nullable = true, unique = true)
    private String sexo;

    @Column(name = "primeiraconsulta", nullable = true, unique = true)
    private Boolean primeiraConsulta;

    @Column(name = "sintomasgripe",nullable = true, unique = true)
    private Boolean sintomas;
    
    @Column(name = "termouso")
    private Boolean termoUso;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "triagem",
    joinColumns = @JoinColumn(name = "fk_paci_doe"),
    inverseJoinColumns = @JoinColumn(name = "fk_doe_paci"))
    private Set<Doenca> doenca = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_end_paci")
    private Endereco endereco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_usu_paci")
    private Usuario usuario;

    // Metodo Construtor
    public Paciente() {}
    
    // Metodo Construtor com Atributos
	public Paciente(Integer idPaci, String nomePaci, String cpf, LocalDate dataNasc, String rg, String celular,
			String sexo, Boolean primeiraConsulta, Boolean sintomas, Boolean termoUso, Set<Doenca> doenca,
			Endereco endereco, Usuario usuario) {
		this.idPaci = idPaci;
		this.nomePaci = nomePaci;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
		this.rg = rg;
		this.celular = celular;
		this.sexo = sexo;
		this.primeiraConsulta = primeiraConsulta;
		this.sintomas = sintomas;
		this.termoUso = termoUso;
		this.doenca = doenca;
		this.endereco = endereco;
		this.usuario = usuario;
	}

	public Integer getIdPaci() {
		return idPaci;
	}

	public void setIdPaci(Integer idPaci) {
		this.idPaci = idPaci;
	}

	public String getNomePaci() {
		return nomePaci;
	}

	public void setNomePaci(String nomePaci) {
		this.nomePaci = nomePaci;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Boolean getPrimeiraConsulta() {
		return primeiraConsulta;
	}

	public void setPrimeiraConsulta(Boolean primeiraConsulta) {
		this.primeiraConsulta = primeiraConsulta;
	}

	public Boolean getSintomas() {
		return sintomas;
	}

	public void setSintomas(Boolean sintomas) {
		this.sintomas = sintomas;
	}

	public Boolean getTermoUso() {
		return termoUso;
	}

	public void setTermoUso(Boolean termoUso) {
		this.termoUso = termoUso;
	}

	public Set<Doenca> getDoenca() {
		return doenca;
	}

	public void setDoenca(Set<Doenca> doenca) {
		this.doenca = doenca;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idPaci);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(idPaci, other.idPaci);
	}
}
