package com.projeto.Servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Endereco;
import com.projeto.Repositorios.EnderecoRepositorio;

@Service
public class EnderecoServico {
	
	@Autowired
	private EnderecoRepositorio repositorio;
	
	@Transactional(readOnly = true)
	public List<Endereco> findAll(){
		return repositorio.findAll();
	}
}
