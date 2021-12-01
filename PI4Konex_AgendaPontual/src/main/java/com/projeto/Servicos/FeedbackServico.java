package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Dto.AvaliacoesNegativasDTO;
import com.projeto.Dto.AvaliacoesPositivasDTO;
import com.projeto.Dto.FeedbackCliMedDTO;
import com.projeto.Dto.MelhoresFeedbacksDTO;
import com.projeto.Entidades.Clinica;
import com.projeto.Entidades.Consulta;
import com.projeto.Entidades.Feedback;
import com.projeto.Entidades.Medico;
import com.projeto.Repositorios.FeedbackRepositorio;
import com.projeto.Repositorios.FeedbackRepositorio2;

@Service
public class FeedbackServico {

	@Autowired
	private FeedbackRepositorio feeRepo;

	@Autowired
	private FeedbackRepositorio2 feeRepo2;

	@Autowired
	private ConsultaServico consServ;

	@Autowired
	private MedicoServico medServ;

	@Autowired
	private ClinicasServico cliServ;

	@Transactional(readOnly = false)
	public void feedbackCadastro(Integer idCons, Feedback feedback) {
		Consulta consulta = consServ.buscaConsultaPorId(idCons);
		Feedback feed = feeRepo2.save(feedback);
		consulta.setFeedback(feed);
		consServ.cadastro(consulta);
		Medico medico = medServ.buscaMedicoPorId(consulta.getMedico().getIdMed());
		Clinica clinica = cliServ.pesquisaClinicaPorId(consulta.getMedico().getClinica().getIdCli());
		algoritmoPontuação(medico, clinica, feedback);
	}

	@Transactional(readOnly = true)
	public List<MelhoresFeedbacksDTO> buscaFeedbackPorMedico(Integer idMed) {
		return feeRepo.buscaFeedbackPorMedico(idMed);
	}

	@Transactional(readOnly = true)
	public AvaliacoesPositivasDTO buscaPositiva(Integer idMed) {
		return feeRepo.buscaAvaPositiva(idMed);
	}

	@Transactional(readOnly = true)
	public AvaliacoesNegativasDTO buscaNegativa(Integer idMed) {
		return feeRepo.buscaAvaNegativa(idMed);
	}

	@Transactional(readOnly = true)
	public List<FeedbackCliMedDTO> listaFeedback(Integer idMed) {
		return feeRepo.listaFeedback(idMed);
	}

	@Transactional(readOnly = true)
	public Feedback buscaFeedPorId(Integer id) {
		return feeRepo2.buscaFeedPorId(id);
	}

	@Transactional(readOnly = true)
	public List<FeedbackCliMedDTO> listaFeedPorID(Integer idFeed) {
		return feeRepo.listaFeedporId(idFeed);
	}

	public void algoritmoPontuação(Medico medico,
			Clinica clinica, Feedback feedback) {
		
		int pontosPadrao = 50;
		int pontuaBase;
		int percentual;
		int baseRedimento;
		int pontosAtualMed = medico.getPontos(); 
		int pontosAtualCli = clinica.getPontos();
		int pontuaFinalMed;
		int pontuaFinalCli;

		//gera media
		baseRedimento = (feedback.getAvaliacao() + feedback.getPontualidade()) / 2;
		percentual = (100 * baseRedimento )/10;//descobre o percentual

		//
		if(percentual <= 45){
			pontuaBase = (50 * percentual) / 100;

			//Atualiza a pontuacao
			if(feedback.isParaMed() && feedback.isParaCli() == false){
				//gera a pontuacao final: É retirado pontos
				pontuaFinalMed  = pontosAtualMed + pontuaBase; 

				medico.setPontos(pontuaFinalMed);
				//atualiza pontuação
				medServ.atualizaMedico(medico);

			}else if(feedback.isParaMed() == false && feedback.isParaCli()){
				//gera a pontuacao final: É retirado pontos
				pontuaFinalCli  = pontosAtualCli + pontuaBase; 

				clinica.setPontos(pontuaFinalCli);
				cliServ.atualizaClinica(clinica);

			}else{

				//gera a pontuacao final: É acrescentado pontos
				pontuaFinalMed = pontosAtualMed - pontuaBase; 
				pontuaFinalCli = pontosAtualCli - pontuaBase; 

				//clicou para ambos
				medico.setPontos(pontuaFinalMed);
				clinica.setPontos(pontuaFinalCli);

				//atualiza pontuação
				medServ.atualizaMedico(medico);
				cliServ.atualizaClinica(clinica);
			}

		}else 
			pontuaBase = (pontosPadrao * percentual) / 100;

			//Atualiza a pontuacao
			if(feedback.isParaMed() && feedback.isParaCli() == false){

				//gera a pontuacao final: É retirado pontos
				pontuaFinalMed  = pontosAtualMed - pontuaBase; 

				medico.setPontos(pontuaFinalMed);
				//atualiza pontuação
				medServ.atualizaMedico(medico);

			}else if(feedback.isParaMed() == false && feedback.isParaCli()){
				//gera a pontuacao final: É retirado pontos
				pontuaFinalCli  = pontosAtualCli - pontuaBase; 

				clinica.setPontos(pontuaFinalCli);
				cliServ.atualizaClinica(clinica);

			}else{

				//gera a pontuacao final: É acrescentado pontos
				pontuaFinalMed  = pontosAtualMed - pontuaBase; 
				pontuaFinalCli = pontosAtualCli - pontuaBase; 

				//clicou para ambos
				medico.setPontos(pontuaFinalMed);
				clinica.setPontos(pontuaFinalCli);

				//atualiza pontuação
				medServ.atualizaMedico(medico);
				cliServ.atualizaClinica(clinica);
			}
	}

}
