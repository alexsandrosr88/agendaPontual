package com.projeto.Repositorios;

import java.util.List;

import com.projeto.Dto.HorariosQuaDTO;
import com.projeto.Dto.HorariosQuiDTO;
import com.projeto.Dto.HorariosSabDTO;
import com.projeto.Dto.HorariosSegDTO;
import com.projeto.Dto.HorariosSexDTO;
import com.projeto.Dto.HorariosTerDTO;
import com.projeto.Dto.MedicoResumoDTO;
import com.projeto.Dto.QuantidadeAtendimentosDTO;
import com.projeto.Entidades.Medico;


public interface MedicoRepositorio{
    
	List<Medico> listaMedPorCli(Integer id);
    
    List<Medico> findAll();
     
    List<Medico> buscaMedCompleta(Integer cidade, Integer bairro, String espec,
			String sexMas, String sexFem,Float valorMin , Float valorMax, 
			Integer minExp, Integer maxExp);

    List<Medico> filtraMedCli(Integer esp, Integer cidade, Integer clinica,
	        String sexMas, String sexFem,Float valorMin, Float valorMax);
    
    MedicoResumoDTO medicoResumo(Integer idMed);
    
    QuantidadeAtendimentosDTO qteAtendimentos(Integer idMed);
    
    List<HorariosSegDTO> buscaSeg(Integer idMed);
    
    List<HorariosTerDTO> buscaTer(Integer idMed);
    
    List<HorariosQuaDTO> buscaQua(Integer idMed);
    
    List<HorariosQuiDTO> buscaQui(Integer idMed);
    
    List<HorariosSexDTO> buscaSex(Integer idMed);
    
    List<HorariosSabDTO> buscaSab(Integer idMed);

    Medico infoMed(Integer idMed);
 
}
