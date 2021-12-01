package com.projeto.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.Dto.UltimoIdDTO;
import com.projeto.Repositorios.UltimoIdDTORepositorio;

@Service
public class UltimoIdDTOServico {
	
	@Autowired
	private UltimoIdDTORepositorio ultRepo;
	
	public UltimoIdDTO buscaUtimoId() {
		return ultRepo.buscaUltimo();
	}
}
