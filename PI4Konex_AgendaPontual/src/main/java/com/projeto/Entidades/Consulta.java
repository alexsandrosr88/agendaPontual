package com.projeto.Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;

	// Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcons")
    
    private Integer idConsulta;
    
    @Column(name = "dtagendada")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
   
    private LocalDate dtAgendada;
    

    @Column(nullable = true)
    private LocalTime hora;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_med_cons", nullable = true)
    private Medico medico;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_paci_cons", nullable = true)
    private Paciente paciente;
    
    @Column(nullable = true, name = "informacoesadic", columnDefinition = "Text")
    private String informaAdicio;

    @Column(nullable = true)
    private boolean confirmada;

    @Column(nullable = true)
    private boolean retorno;

    @Column(name = "horachegada", nullable = true)
    private LocalTime horaChegada;

    @Column(name = "horasaida", nullable = true)
    private LocalTime horaSaida;

    @Column(name = "duracao", nullable = true)
    private LocalTime duracao;

    @Column(name = "concluida", nullable = true)
    private boolean concluida;

    @Column(name = "naocompareceu", nullable = true)
    private boolean naoCompareceu;

    @Column(name = "cancelada", nullable = true)
    private boolean cancelada;
    
    @OneToOne
    @JoinColumn(name = "fk_rec_cons", nullable = true)
    private Receita receita;
    
    @OneToOne
    @JoinColumn(name = "fk_feed_cons", nullable = true)
    private Feedback feedback;
      
    @OneToOne
    @JoinColumn(name = "fk_pag_cons", nullable = true)
    private Pagamento pagamento;

    // Metodo Construtor
    
    public Consulta(){}
    // Metodo Construtor com Atributos
	public Consulta(Integer idConsulta, LocalDate dtAgendada, LocalTime hora, Medico medico, Paciente paciente,
			String informaAdicio, boolean confirmada, boolean retorno, LocalTime horaChegada, LocalTime horaSaida,
			LocalTime duracao, boolean concluida, boolean naoCompareceu, boolean cancelada, Receita receita,
			Feedback feedback, Pagamento pagamento) {
		this.idConsulta = idConsulta;
		this.dtAgendada = dtAgendada;
		this.hora = hora;
		this.medico = medico;
		this.paciente = paciente;
		this.informaAdicio = informaAdicio;
		this.confirmada = confirmada;
		this.retorno = retorno;
		this.horaChegada = horaChegada;
		this.horaSaida = horaSaida;
		this.duracao = duracao;
		this.concluida = concluida;
		this.naoCompareceu = naoCompareceu;
		this.cancelada = cancelada;
		this.receita = receita;
		this.feedback = feedback;
		this.pagamento = pagamento;
	}
	
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
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
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public String getInformaAdicio() {
		return informaAdicio;
	}
	public void setInformaAdicio(String informaAdicio) {
		this.informaAdicio = informaAdicio;
	}
	public boolean isConfirmada() {
		return confirmada;
	}
	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}
	public boolean isRetorno() {
		return retorno;
	}
	public void setRetorno(boolean retorno) {
		this.retorno = retorno;
	}
	public LocalTime getHoraChegada() {
		return horaChegada;
	}
	public void setHoraChegada(LocalTime horaChegada) {
		this.horaChegada = horaChegada;
	}
	public LocalTime getHoraSaida() {
		return horaSaida;
	}
	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}
	public LocalTime getDuracao() {
		return duracao;
	}
	public void setDuracao(LocalTime duracao) {
		this.duracao = duracao;
	}
	public boolean isConcluida() {
		return concluida;
	}
	public void setConcluida(boolean concluida) {
		this.concluida = concluida;
	}
	public boolean isNaoCompareceu() {
		return naoCompareceu;
	}
	public void setNaoCompareceu(boolean naoCompareceu) {
		this.naoCompareceu = naoCompareceu;
	}
	public boolean isCancelada() {
		return cancelada;
	}
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	public Receita getReceita() {
		return receita;
	}
	public void setReceita(Receita receita) {
		this.receita = receita;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idConsulta);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(idConsulta, other.idConsulta);
	}
}
