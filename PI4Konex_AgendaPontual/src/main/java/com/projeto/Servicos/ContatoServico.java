package com.projeto.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Contato;
import com.projeto.Repositorios.ContatoRepository;

@Service
public class ContatoServico {
	
	@Autowired
	private ContatoRepository conRepo;
	
	@Transactional(readOnly = false)
	public void cadastro(Contato contato) {
		conRepo.save(contato);
	}
}
