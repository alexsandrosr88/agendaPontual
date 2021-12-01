package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Dto.AvaliacoesNegativasDTO;
import com.projeto.Dto.AvaliacoesPositivasDTO;
import com.projeto.Dto.FeedbackCliMedDTO;
import com.projeto.Dto.MelhoresFeedbacksDTO;


public interface FeedbackRepositorio {
	
	List<MelhoresFeedbacksDTO> buscaFeedbackPorMedico(Integer idMed);
	
	AvaliacoesPositivasDTO buscaAvaPositiva(Integer idMed);
	
	AvaliacoesNegativasDTO buscaAvaNegativa(Integer idMed);

	List<FeedbackCliMedDTO> listaFeedback(Integer idMed);

	List<FeedbackCliMedDTO> listaFeedporId(Integer idFeed);
}
