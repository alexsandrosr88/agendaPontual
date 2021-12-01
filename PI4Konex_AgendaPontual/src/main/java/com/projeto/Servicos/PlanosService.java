
package com.projeto.Servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projeto.Entidades.Planos;
import com.projeto.Repositorios.PlanosRepository;
//
@Service
public class PlanosService {

	@Autowired
	private PlanosRepository plR;
	
	@Transactional(readOnly = false)
	public void createPlanos(Planos planos) {
		plR.save(planos);
	}
}