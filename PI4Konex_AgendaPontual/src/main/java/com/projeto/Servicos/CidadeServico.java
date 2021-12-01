package com.projeto.Servicos;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Cidade;
import com.projeto.Repositorios.CidadeRepositorio;


@Service
public class CidadeServico {
	
	@Autowired
	private CidadeRepositorio repoCid;
	
	@Transactional(readOnly = true)
	public List<Cidade> findAll(){
		return repoCid.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Object[]> buscaBairroPorCidade(Integer id){
		return repoCid.buscaBairroPorCidade(id);
	}	
}
