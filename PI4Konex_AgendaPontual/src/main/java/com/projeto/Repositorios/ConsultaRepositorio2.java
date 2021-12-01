package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Dto.ConsultaSemFeedDTO;
import com.projeto.Dto.ConsultasPacienteDTO;

public interface ConsultaRepositorio2 {
	
	List<ConsultaSemFeedDTO> pesquisaConsultaSemFeed(Integer paciente);
	
	List<ConsultasPacienteDTO> buscaConsultaPorPaciente(Integer paciente);
	
	ConsultasPacienteDTO buscaConsultaPorId(Integer idCons);
}
