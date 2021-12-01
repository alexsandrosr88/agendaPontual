package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Dto.HorariosQuaDTO;
import com.projeto.Dto.HorariosQuiDTO;
import com.projeto.Dto.HorariosSabDTO;
import com.projeto.Dto.HorariosSegDTO;
import com.projeto.Dto.HorariosSexDTO;
import com.projeto.Dto.HorariosTerDTO;
import com.projeto.Dto.MedicoResumoDTO;
import com.projeto.Dto.QuantidadeAtendimentosDTO;
import com.projeto.Entidades.Medico;
import com.projeto.Repositorios.MedicoRepositorio;
import com.projeto.Repositorios.MedicojpaRepository;

@Service
public class MedicoServico {
	

	@Autowired
	private MedicoRepositorio medRepo;
	
	@Autowired
	private MedicojpaRepository medRepo2;

	@Transactional(readOnly = true)
	public List<Medico> listaMedPorCli(Integer id) {
		return medRepo.listaMedPorCli(id);
	}
	@Transactional(readOnly = true)
	public List<Medico> findAll() {
		return medRepo.findAll(); 
	}
	@Transactional(readOnly = true)
	public List<Medico> buscaMedCompleta(Integer cidade, Integer bairro, String espec,
			String sexMas, String sexFem,Float valorMin , Float valorMax, 
			Integer minExp, Integer maxExp) {
		return medRepo.buscaMedCompleta(cidade, bairro, espec, sexMas, sexFem, valorMin,
				valorMax, minExp, maxExp);
	}

	@Transactional(readOnly = true)
	public List<Medico> filtraMedCli(Integer idEsp, Integer idCid, Integer idCli,
	       String sexMas, String sexFem,Float valorMin, Float valorMax){
		return medRepo.filtraMedCli(idEsp, idCid, idCli, sexMas, sexFem, valorMin, valorMax);
	}
	
	@Transactional(readOnly = true)
	public MedicoResumoDTO medicoResumo(Integer IdMed){

		return medRepo.medicoResumo(IdMed);
	}
	
	@Transactional(readOnly = true)
	public QuantidadeAtendimentosDTO buscaQteAtendimento(Integer idMed) {
		return medRepo.qteAtendimentos(idMed);
	}
	
	@Transactional(readOnly = true)
	public List<HorariosSegDTO> buscaSeg(Integer idMed) {
		return medRepo.buscaSeg(idMed);
	}
	
	@Transactional(readOnly = true)
	public List<HorariosTerDTO> buscaTer(Integer idMed) {
		return medRepo.buscaTer(idMed);
	}
	
	@Transactional(readOnly = true)
	public List<HorariosQuaDTO> buscaQua(Integer idMed) {
		return medRepo.buscaQua(idMed);
	}
	
	@Transactional(readOnly = true)
	public List<HorariosQuiDTO> buscaQui(Integer idMed) {
		return medRepo.buscaQui(idMed);
	}
	@Transactional(readOnly = true)
	public List<HorariosSexDTO> buscaSex(Integer idMed) {
		return medRepo.buscaSex(idMed);
	}
	@Transactional(readOnly = true)
	public List<HorariosSabDTO> buscaSab(Integer idMed) {
		return medRepo.buscaSab(idMed);
	}

	@Transactional(readOnly = true)
	public Medico infoMed(Integer idMed){
		return medRepo.infoMed(idMed);
	}

	@Transactional(readOnly = false)
	public Medico atualizaMedico(Medico med){
		Integer idMed = med.getIdMed();
		Medico medico = medRepo.infoMed(idMed);
		medico.setNomeMed(med.getNomeMed());
		medico.setCpfMed(med.getCpfMed());
		medico.setSexoMed(med.getSexoMed());
		medico.setDataNasc(med.getDataNasc());
		medico.setRgMed(med.getRgMed());
		medico.setCelular(med.getCelular());
		return medRepo2.save(medico);
		
	}
	
	@Transactional(readOnly = true)
	public Medico buscaMedicoPorId(Integer idMed) {
		return medRepo2.buscaMedicoPorId(idMed);
	}
	
	@Transactional(readOnly = false)
	public void atualizaPontuacao(Integer idMed, Integer ponto) {
		Medico med = medRepo2.buscaMedicoPorId(idMed);
		med.setPontos(ponto);
		medRepo2.save(med);
	}
}