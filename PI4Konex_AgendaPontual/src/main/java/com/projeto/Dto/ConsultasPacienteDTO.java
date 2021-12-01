package com.projeto.Dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;

public class ConsultasPacienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer idCons;
	private Integer idMed;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dtAgendada;
	private LocalTime hora;
	private String especialidade;
	private String foto;
	private String nomeMed;
	private String crm;
	private String sobreMim;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String sala;
	private String bairro;
	private String cep;
	private String telefone;
	private Float valor;
	
	public ConsultasPacienteDTO() {}

	public ConsultasPacienteDTO(Integer idCons, Integer idMed, LocalDate dtAgendada, LocalTime hora, String especialidade, byte [] foto,
			String nomeMed, String crm, String sobreMim, String logradouro, Integer numero, String complemento, String sala,
			String bairro, String cep, String telefone, Float valor) {
		this.idCons = idCons;
		this.idMed = idMed;
		this.dtAgendada = dtAgendada;
		this.hora = hora;
		this.especialidade = especialidade;
		this.foto = Base64.encodeBase64String(foto);
		this.nomeMed = nomeMed;
		this.crm = crm;
		this.sobreMim = sobreMim;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.sala = sala;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone = telefone;
		this.valor = valor;
	}
	
	public Integer getIdCons() {
		return idCons;
	}

	public void setIdCons(Integer idCons) {
		this.idCons = idCons;
	}

	public Integer getIdMed() {
		return idMed;
	}

	public void setIdMed(Integer idMed) {
		this.idMed = idMed;
	}

	public LocalDate getDtAgendada() {
		return dtAgendada;
	}

	public void setDtAgendada(LocalDate dtAgendada) {
		this.dtAgendada = dtAgendada;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(byte [] foto) {
		this.foto = Base64.encodeBase64String(foto);
	}

	public String getNomeMed() {
		return nomeMed;
	}

	public void setNomeMed(String nomeMed) {
		this.nomeMed = nomeMed;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	
	public String getSobreMim() {
		return sobreMim;
	}

	public void setSobreMim(String sobreMim) {
		this.sobreMim = sobreMim;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCons);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultasPacienteDTO other = (ConsultasPacienteDTO) obj;
		return Objects.equals(idCons, other.idCons);
	}
}
