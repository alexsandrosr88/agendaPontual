package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Bairro;
import com.projeto.Repositorios.BairroRepositorio;

@Service
public class BairroServico {
	
	@Autowired
	private BairroRepositorio repoBai;
	
	@Transactional(readOnly = true)
	public List<Bairro> buscaBairro(){
		return repoBai.findAll();
	}
}
