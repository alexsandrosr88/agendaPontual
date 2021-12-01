package com.projeto.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clinica")
public class Clinica implements Serializable{
	private static final long serialVersionUID = 1L;

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcli")
	private int idCli;

	@Column(name = "dtcriacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCriaCli;

	@Column(name = "nomecli", nullable = true)
	private String nomeCli;

	@Column(name = "nomemedresp", nullable = true)
	private String nomeMedResp;

	@Column(nullable = true, unique = true)
	private String cnpj;

	@Column(nullable = true, unique = true)
	private String rg;

	@Column(name = "qteassentos", nullable = true)
	private int qntAssentos;

	@Column(name = "fone", nullable = true)
	private String telefone;
	
	@Column(nullable = true)
	private Integer pontos;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "fk_plan_cli", nullable = true)
	private Planos plano;

	@ManyToOne
	@JoinColumn(name = "fk_end_cli", nullable = true)
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "fk_usu_cli", nullable = true)
	private Usuario usuario;

	
	// Metodo Construtor
	public Clinica() {

	}
	// Metodo Construtor com Atributos
	public Clinica(int idCli, LocalDateTime dataCriaCli, String nomeCli, String nomeMedResp, String cnpj, String rg,
			int qntAssentos, String telefone, Planos plano, Endereco endereco, Integer pontos, Usuario usuario) {
		this.idCli = idCli;
		this.dataCriaCli = dataCriaCli;
		this.nomeCli = nomeCli;
		this.nomeMedResp = nomeMedResp;
		this.cnpj = cnpj;
		this.rg = rg;
		this.qntAssentos = qntAssentos;
		this.telefone = telefone;
		this.plano = plano;
		this.endereco = endereco;
		this.pontos = pontos;
		this.usuario = usuario;
	}
	public int getIdCli() {
		return idCli;
	}
	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}
	public LocalDateTime getDataCriaCli() {
		return dataCriaCli;
	}
	public void setDataCriaCli(LocalDateTime dataCriaCli) {
		this.dataCriaCli = dataCriaCli;
	}
	public String getNomeCli() {
		return nomeCli;
	}
	public void setNomeCli(String nomeCli) {
		this.nomeCli = nomeCli;
	}
	public String getNomeMedResp() {
		return nomeMedResp;
	}
	public void setNomeMedResp(String nomeMedResp) {
		this.nomeMedResp = nomeMedResp;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public int getQntAssentos() {
		return qntAssentos;
	}
	public void setQntAssentos(int qntAssentos) {
		this.qntAssentos = qntAssentos;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Planos getPlano() {
		return plano;
	}
	public void setPlano(Planos plano) {
		this.plano = plano;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;		
	}
	public Integer getPontos() {
		return pontos;
	}
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
