package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.tomcat.util.codec.binary.Base64;

public class MedicoResumoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String foto;
	private String nome;
	private Integer pontos;
	private String especialidade;
	private String biografia;
	private String crm;
	private String dataEmissao;
	private String logradouro;
	private String complemento;
	private String telefone;
	private String valor;
	
	public MedicoResumoDTO() {}


	public MedicoResumoDTO(Integer id, byte [] foto, String nome, Integer pontos, String especialidade, String biografia, String crm,
			String dataEmissao, String logradouro, String complemento, String telefone, String valor) {
		this.id = id;
		this.foto = Base64.encodeBase64String(foto);
		this.nome = nome;
		this.pontos = pontos;
		this.especialidade = especialidade;
		this.biografia = biografia;
		this.crm = crm;
		this.dataEmissao = dataEmissao;
		this.logradouro = logradouro;
		this.complemento = complemento;
		this.telefone = telefone;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(byte [] foto) {
		this.foto = Base64.encodeBase64String(foto);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(String emissao) {
		this.dataEmissao = emissao;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String valor) {
		this.valor = valor;
	}
}
