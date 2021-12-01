package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Doenca;
import com.projeto.Repositorios.DoencaRepositorio;

@Service
public class DoencaServico {
	
	@Autowired
	private DoencaRepositorio doeRepo;
	
	@Transactional(readOnly = true)
	public List<Doenca> buscaDoencaPorMedico(Integer Idmed){
		return doeRepo.buscaDoencasPorMedico(Idmed);
	}
}
